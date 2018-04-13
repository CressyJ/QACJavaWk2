//A class for any item that can be stored in the Library
//It is abstract so that it can't be instantiated
abstract public class Item {
	//Universal unique identifier for all items
	private static int nextID;
	private int iD;
	private String title, author, published;
	Person borrowedBy;
	
	//initialise nextID to 1, just so we have no ID 0
	static { 
		nextID = 1;
	}
	
	//private function to return and increment the nextID field
	//Alternatively, I could just enter nextID++ into the ID field
	//in the Constructor for Item
	private static int getNextID() { return nextID++; }
	
//Constructors
	//This constructor will always be called, so we can always have unique IDs
	protected Item() {
		iD = getNextID();
		borrowedBy = null;
	}
	
	//parameter constructor that handles title and author
	public Item(String title, String author) {
		this();
		setTitle(title);
		setAuthor(author);
	}
	
//One of the next two constructors should be called by all child classes
	//parameter constructor for being given a String for the published date
	public Item(String title, String author, String published) {
		this(title, author);
		setPublished(published);
	}
	
	//preferred parameter constructor that uses the enterDate version
	public Item(String title, String author, int dayPublished, int monthPublished, int yearPublished) {
		this(title, author);
		setPublished(dayPublished, monthPublished, yearPublished);
	}
	
//get methods
	public int getID()				{ return iD; }
	public String getTitle()		{ return title; }
	public String getAuthor()		{ return author; }
	public String getPublished()	{ return published; }
	public Person getBorrowedBy()	{ return borrowedBy; }
	public boolean isBorrowed() {
		if(borrowedBy != null) return true;
		return false;
	}
	
//set methods
	public void setTitle(String title)		{ this.title = title; }
	public void setAuthor(String author)	{ this.author = author; }
	public void setBorrowedBy(Person p)		{ borrowedBy = p; }
	
	//as published is a date, it needs to conform to some sort of format
	public boolean setPublished(String published) {
		boolean outcome = false;
		
		//if the date can't be formatted, then it needs to be rejected
		published = formatDate(published);
		//formatDate returns "invalid" if there's no way of rationalising the input into a date
		outcome = published.equals("invalid") ? false : true;
		
		if(outcome) this.published = published;
		
		return outcome;
	}
//Overloaded method
	//if we get given three digits, do a version that avoids the lengthy validation process
	public boolean setPublished(int day, int month, int year) {
		published = enterDate(day, month, year);
		
		return true;
	}
	
//other methods
	//nicely formatted String for the console
	@Override
	public String toString() {
		String output = "";
		
		output += "Item: " + Integer.toString(iD) + "\n" + title + "\nAuthor: " + author + 
				"\nPublished: " + published;
		
		return output;
	}
	
	//CSV output that should be overridden by 
	public String toCSV() {
		String output = "";
		
		output += Integer.toString(iD) + "," + title + "," + author + "," + published;
		
		return output;
	}
	
	//utility function to enter three integers and get a date String easily
	//made static so that it can be used elsewhere
	public static String enterDate(int day, int month, int year) {
		//minor validation to make sure the day and month are the right way around
		if(day <= 12 && (month > 12 && month <= 31)){
			int temp = day;
			day = month;
			month = temp;
		}
		
		//make our years four-digit for ease of use
		if(year > 18 && year < 100) year += 1900;
		else if(year <= 18) year += 2000;
		
		//simple DD-MM-YYYY format
		String output = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);
		
		return output;
	}
	
	//utility function to format any String provided into a date
	private String formatDate(String input) {
		//enforce lower case to make life easier
		input = input.toLowerCase();
		
		//these variables will be used to store that segment whilst reformatting occurs
		int year, month, day;
		year = month = day = 0;
		
		//When we split the date up, we need somewhere to store it
		//by default dateSegments is a 1-String array just so that it's not uninitialised
		String[] dateSegments = new String[1];
		dateSegments[0] = "invalid";
		
		/* run all formatting checks first to turn any valid input into a date
		 * check for slashes, dashes, dots, commas and spaces, as dates are usually formatted in any of these ways
		 * this will overwrite the version above where it currently just reads invalid if successful
		 */
		String[] dividers = { "/", "-", ".", ",", " " };
		for(String s : dividers) if(input.contains(s)) dateSegments = input.split(s);
		
		if(dateSegments[0].equals("invalid")) input = dateSegments[0];
		else {
			//loop through each segment of the date String in turn and try to work out what is what
			for(String s : dateSegments) {
				//if the numeric value of the integer is greater than 31 it's definitely a year value
				if(Integer.parseInt(s) > 31) {
					year = Integer.parseInt(s);
					continue;
				}
				
				//if the year has been entered as 00, then it's the year 2000,
				//as it's probably safe to say we don't have anything published in the year 0AD
				if(Integer.parseInt(s) == 0) {
					year = 2000;
					continue;
				}
				
				//if the numeric value is greater than 12, it can't be a month
				//So if the year is set as well, it must be a day
				if(Integer.parseInt(s) > 12 && year > 0) {
					day = Integer.parseInt(s);
					continue;
				}
				
				//Check for text entries of months first
				boolean monthSet = false;
				switch(s) {
				case "jan":
				case "january":
					month = 1;
					monthSet = true;
					break;
				case "feb":
				case "february":
					month = 2;
					monthSet = true;
					break;
				case "mar":
				case "march":
					month = 3;
					monthSet = true;
					break;
				case "apr":
				case "april":
					month = 4;
					monthSet = true;
					break;
				case "may":
					month = 5;
					monthSet = true;
					break;
				case "jun":
				case "june":
					month = 6;
					monthSet = true;
					break;
				case "jul":
				case "july":
					month = 7;
					monthSet = true;
					break;
				case "aug":
				case "august":
					month = 8;
					monthSet = true;
					break;
				case "sep":
				case "september":
					month = 9;
					monthSet = true;
					break;
				case "oct":
				case "october":
					month = 10;
					monthSet = true;
					break;
				case "nov":
				case "november":
					month = 11;
					monthSet = true;
					break;
				case "dec":
				case "december":
					month = 12;
					monthSet = true;
					break;
				}
				
				//if the above switch statement had a result, step to the next segment
				if(monthSet) continue;
				
				//if all of the above have been checked and we've still got nothing, it's time to go through in order
				//and hope that we've been given it in the correct order				
				if(day > 0 && month > 0) year = Integer.parseInt(s);
				else if(day > 0 && year > 0) month = Integer.parseInt(s);
				else day = Integer.parseInt(s);
			}
			
			//if anything is still 0 after that we've got a problem
			if (day == 0 || month == 0 || year == 0) input = "invalid";
			
			if(!input.equals("invalid")) input = enterDate(day, month, year);
		}
		
		//then check to see if the post-formatting result contains anything other than a date
		String[] check = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
			"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "/", ".", ",", " " };
		for(String s : check) {
			if(input.contains(s)) input = "invalid";
			break;
		}
		
		return input;
	}
}
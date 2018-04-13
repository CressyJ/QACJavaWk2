public class Person {
	private String name, jobTitle;
	private int age;
	
	Person(){ }
	
	Person(String name, int age, String jobTitle){
		setName(name);
		setAge(age);
		setJobTitle(jobTitle); 
	}
	
	public String getName() { return name; }
	public int getAge() { return age; }
	public String getJobTitle() { return jobTitle; }
	
	public void setName(String name) { this.name = name; }
	public void setAge(int age) { this.age = age; }
	public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
	
	@Override
	public String toString() {
		return "Name:\t\t" + name + "\nAge:\t\t" + age + "\nJob Title:\t" + jobTitle + "\n";
	}
}
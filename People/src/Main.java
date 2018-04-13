public class Main {
	public static void main(String[] args) {
		PersonManager pm = PersonManager.getPersonManager();
		
		Person a = new Person();
		a.setName("Aaron");
		a.setAge(21);
		a.setJobTitle("Student");
		
		//System.out.println(a.toString());
		
		pm.addPerson(a);
		
		Person b = new Person("Brian", 25, "Chef");
		pm.addPerson(b);
		
		pm.addPerson("Chris", 30, "Driver");
		pm.addPerson("Dave", 35, "Builder");
		pm.addPerson("Eric", 29, "Teacher");
		
		//pm.listEverybody();
		
		pm.getPersonByName("Dave");
		pm.getPersonByName("NotDave");
	}
}
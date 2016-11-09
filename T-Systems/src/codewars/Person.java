package CodeWars;

public class Person {
    String name;

    public Person(String personName) {
	name = personName;
    }

    public String greet(String yourName) {
	return String.format("Hi %s, my name is %s", name, yourName);
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	Person nik  = new Person("Nik");
	System.out.println(nik.greet("Alice"));

    }

}

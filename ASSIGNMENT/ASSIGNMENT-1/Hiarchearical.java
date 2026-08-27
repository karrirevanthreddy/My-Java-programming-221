package lab;
class Father{
	String name = "GURESWARA REDDY\n";
	int age = 43;
	void husband() {
		System.out.println(name  + age + "\nI AM HEAD OF THE HOUSE\n");
	}
}
class Mother extends Father{
	String name = "PAVANI\n";
	int age = 36;
	void wife() {
		System.out.println(name + age + "\nI AM THE HOMEMINISTER OF THE HOUSE\n");
	}
}
class Child extends Father{
	String name = "REVANTH REDDY\n";
	int age = 18;
	void kid() {
		System.out.println(name + age + "\nI AM A SINGLE KID\n");
	}
}
public class Hiarchearical {
	public static void main(String[] args) {
		Child c = new Child();
		Mother m = new Mother();
		c.husband();
		m.wife();
		c.kid();
	}

}

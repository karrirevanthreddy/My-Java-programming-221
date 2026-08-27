package lab;
interface In1{
	String name = "Revanth";
	void sample();
}
interface In2{
	int rollno = 221;
	void demo();
}

public class Multiple implements In1,In2 {
	public void sample() {
		System.out.println( name + "\nHe is second year");
	}
	public void demo() {
		System.out.println("From CSM-D");
	}
	public static void main(String[] args) {
		Multiple m = new Multiple();
		m.sample();
		m.demo();
	}

}

package lab;
class Ek{
	String name = "Revanth";
	void innocent() {
		System.out.println(name + "\n HE IS INNOCENT");
	}
}
class Do extends Ek{
	String name = "Venu";
	void playboy() {
		System.out.println(name + "\n HE IS PLAYBOY");
		System.out.println("INNOCENT AND PLAYBOY ARE DARLINGS ");
	}
}
class Teen extends Do{
	String name = "Jathin";
	void chickmagnet() {
		System.out.println(name + "\n GIRLS FAVOURITE");
		System.out.println("PLAYBOY AND CHICKMAGNET ARE SALEY'S");
	}
}
class Char extends Do{
	String name = "Srikar";
	void fire() {
		System.out.println(name + "\n SHORT TEMPER ");
		System.out.println("PLAYBOY AND SHORT TEMPER ARE BEST FRIENDS");
	}
}

public class Hybrid {
	public static void main(String[] args) {
		Char c = new Char();
		Teen t = new Teen();
		Do   d = new Do();
		d.innocent();
		d.playboy();
		t.chickmagnet();
		c.fire();
		
	}

}

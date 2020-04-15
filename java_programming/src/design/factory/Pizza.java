package design.factory;

public abstract class Pizza {
	String name;
	Dough dough;
	Sauce sauce;
	Veggies veggies[];
	Cheese cheese;
	Pepperoni pepperoni;
	Clams clam;

	abstract void prepare();

	void bake() {
		System.out.println("350度で25分焼く");
	}

	void cut() {
		System.out.println("きる");
	}

	void box() {
		System.out.println("梱包");
	}

	void setName(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}

	public String toString() {
		return "a";
	}
}

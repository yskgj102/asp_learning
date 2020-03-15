package design.decorator;

public abstract class Beverage {
	String description = "unknown drink";

	public String getDescription() {
		return description;
	}

	public abstract double cost();
}

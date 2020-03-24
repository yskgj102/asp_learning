package design.decorator;

import java.io.InputStream;
import java.io.LineNumberInputStream;

public class CoffeeShop {
	public static void main(String[] args) {
		Beverage beverage = new Espresso();
		beverage = new Mocha(beverage);
		System.out.println(beverage.cost());
		System.out.println(beverage.getDescription());
		InputStream in = null;
		in = new LineNumberInputStream(in);

	}
}

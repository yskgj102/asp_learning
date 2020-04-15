package design.factory;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Dough createDoughz() {
		return new ThinCrustDough();
	}

	@Override
	public Sauce createSauce() {
		return new MarinaraSause();
	}

	@Override
	public Cheese createCheese() {
		return new ReggianoCheese();
	}

	@Override
	public Veggies[] createVeggies() {
		Veggies veggies[]= {new Garlic(),new Onion(),new Mashroom,new RedPepper()};
		return veggies;
	}

	@Override
	public Pepperoni createPepperoni() {
		return new Slicedepperoni();
	}

	@Override
	public Clams createClam() {
		return new FreshClams();
	}

}

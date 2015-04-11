package afirstexample;

import java.util.Vector;

public class Customer {

	private final String _name;
	private final Cart cart;
	private final Vector<Rental> _rentals = new Vector<>();

	public Customer (final String name, final Cart cart) {
		_name = name;
		this.cart = cart;
	}

	public void addRental(final Rental rental) {
		_rentals.addElement(rental);
	}

	public String statement () {
		_rentals.forEach(cart::add);

		return cart.generateStatement(_name);
	}

}

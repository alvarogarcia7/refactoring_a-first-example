package afirstexample;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

	private final String _name;
	private final Vector<Rental> _rentals = new Vector<>();

	public Customer(final String name) {
		_name = name;
	}

	public void addRental(final Rental arg) {
		_rentals.addElement(arg);
	}

	public String getName() {
		return _name;
	}

	public String statement() {
		final Cart cart = new Cart();
		final Enumeration<Rental> rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			double thisAmount = 0;
			final Rental each = rentals.nextElement();

			//determine amount for each line
			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += 2;
				if (each.getDaysRented() > 2) {
					thisAmount += (each.getDaysRented() - 2) * 1.5;
				}
				break;
			case Movie.NEW_RELEASE:
				thisAmount += each.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS:
				thisAmount += 1.5;
				if (each.getDaysRented() > 3) {
					thisAmount += (each.getDaysRented() - 3) * 1.5;
				}
				break;
			}

			cart.addRenterPointsFor(each);


			//show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
			cart.totalAmount +=thisAmount;
		}

		//add footer lines
		result+= cart.getAmountOwed();
		result += cart.getEarnedRenterPoints();
		return result;
	}

	private static class Cart {
		public double totalAmount = 0d;
		public int frequentRenterPoints = 0;

		private String getAmountOwed () {
			return "Amount owed is " + String.valueOf(this.totalAmount)+"\n";
		}

		private String getEarnedRenterPoints () {
			return "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		}

		public void addRenterPointsFor (final Rental rental) {
			frequentRenterPoints++;
			//add bonus for a two day new release rental
			if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1) {
				frequentRenterPoints++;
			}
		}
	}

}

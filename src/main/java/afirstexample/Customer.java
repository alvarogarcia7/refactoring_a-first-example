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

	public void addRental(final Rental arg) {
		_rentals.addElement(arg);
	}

	public String getName() {
		return _name;
	}

	public String statement () {
		_rentals.forEach(cart::add);

		return cart.generateStatement(getName());
	}

	public static class Cart {
		private final FigureFormatter formatter;
		public double totalAmount = 0d;
		public int frequentRenterPoints = 0;
		private String figures = "";

		public Cart (final FigureFormatter formatter) {

			this.formatter = formatter;
		}

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

		private double add (final Rental each) {
			double thisAmount = 0;
			switch (each.getMovie().getPriceCode()) {
				case Movie.REGULAR:
					thisAmount += Movie.TYPE_REGULAR.rented(each.getDaysRented());
					break;
				case Movie.NEW_RELEASE:
					thisAmount += Movie.TYPE_NEW_RELEASE.rented(each.getDaysRented());
					break;
				case Movie.CHILDRENS:
					thisAmount += Movie.TYPE_CHILDRENS.rented(each.getDaysRented());
					break;
			}
			this.totalAmount+=thisAmount;

			this.addRenterPointsFor(each);
			this.addToFigures(formatter.formatFigure(each, thisAmount));

			return thisAmount;
		}



		private void addToFigures (final String figure) {
			this.figures += figure;
		}

		public String getFigures () {
			return figures;
		}

		private String generateStatement (final String customerName) {
			String result = "Rental Record for " + customerName + "\n";
			result += this.getFigures();
			result += this.getAmountOwed();
			result += this.getEarnedRenterPoints();
			return result;
		}
	}

	public static class FigureFormatter {

		private String formatFigure (final Rental each, final double thisAmount) {
			return "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
		}
	}
}

package afirstexample;

/**
* Created by alvaro on 11/04/15.
*/
public class Cart {
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

	protected double add (final Rental each) {
		double thisAmount = each.getMovie().getPriceType().rented(each.getDaysRented());
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

	String generateStatement (final String customerName) {
		String result = formatStatement(customerName);
		return result;
	}

	private String formatStatement (final String customerName) {
		String result = "Rental Record for " + customerName + "\n";
		result += this.getFigures();
		result += this.getAmountOwed();
		result += this.getEarnedRenterPoints();
		return result;
	}
}

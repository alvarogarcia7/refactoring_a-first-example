package afirstexample;

/**
* Created by alvaro on 11/04/15.
*/
public class Cart {
	private final Formatter formatter;
	public double totalAmount = 0d;
	public int frequentRenterPoints = 0;
	private Figures figures;

	public Cart (final Figures figures, final Formatter formatter) {
		this.figures = figures;
		this.formatter = formatter;
	}

	private String getAmountOwed () {
		return formatter.formatAmountOwed(totalAmount);
	}

	private String getEarnedRenterPoints () {
		return formatter.formatRenterPoints(frequentRenterPoints);
	}

	public void addRenterPointsFor (final Rental rental) {
		//TODO AGB feature envy
		this.frequentRenterPoints += rental.getMovie().getRenterPoints(rental.getDaysRented());
	}

	protected double add (final Rental rental) {
		//TODO AGB feature envy
		double rentalPrice = rental.getMovie().getPriceType().rented(rental.getDaysRented());
		this.totalAmount+=rentalPrice;

		this.addRenterPointsFor(rental);
		this.addToFigures(new Figure(rental, rentalPrice));

		return rentalPrice;
	}

	private void addToFigures (final Figure figure) {
		figures.add(figure);
	}

	public String getFigures ()
	{
		String figureText = "";
		for (Figure current : figures) {
			//TODO AGB feature envy
			figureText += formatter.format(current);
		}

		return figureText;
	}

	String generateStatement (final String customerName) {
		String result = formatter.formatStatement(customerName, this.getFigures(), this.getAmountOwed(), this
				.getEarnedRenterPoints());
		return result;
	}

}

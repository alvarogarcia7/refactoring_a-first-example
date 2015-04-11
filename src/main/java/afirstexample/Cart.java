package afirstexample;

import java.util.ArrayList;
import java.util.List;

/**
* Created by alvaro on 11/04/15.
*/
public class Cart {
	private final Formatter formatter;
	public double totalAmount = 0d;
	public int frequentRenterPoints = 0;
	private List<Figure> figureList;

	public Cart (final Formatter formatter) {
		this.formatter = formatter;
		this.figureList = new ArrayList<>();
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
		figureList.add(figure);
	}

	public String getFigures ()
	{
		String figuresFromList = "";
		for (Figure current : figureList) {
			figuresFromList += formatter.formatFigure(current.rental, current.rentalAmount);
		}

		return figuresFromList;
	}

	String generateStatement (final String customerName) {
		String result = formatter.formatStatement(customerName, this.getFigures(), this.getAmountOwed(), this
				.getEarnedRenterPoints());
		return result;
	}

	private class Figure {
		private final Rental rental;
		private final double rentalAmount;

		public Figure (final Rental rental, final double rentalAmount) {
			this.rental = rental;
			this.rentalAmount = rentalAmount;
		}
	}
}

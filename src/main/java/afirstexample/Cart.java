package afirstexample;

import java.util.ArrayList;
import java.util.List;

/**
* Created by alvaro on 11/04/15.
*/
public class Cart {
	private final FigureFormatter figureFormatter;
	private final StatementFormatter statementFormatter;
	public double totalAmount = 0d;
	public int frequentRenterPoints = 0;
	private List<Figure> figureList;

	public Cart (final FigureFormatter figureFormatter, final StatementFormatter statementFormatter) {
		this.figureList = new ArrayList<>();
		this.figureFormatter = figureFormatter;
		this.statementFormatter = statementFormatter;
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

	protected double add (final Rental rental) {
		double thisAmount = rental.getMovie().getPriceType().rented(rental.getDaysRented());
		this.totalAmount+=thisAmount;

		this.addRenterPointsFor(rental);
		this.addToFigures(new Figure(rental, thisAmount));

		return thisAmount;
	}

	private void addToFigures (final Figure figure) {
		figureList.add(figure);
	}

	public String getFigures ()
	{
		String figuresFromList = "";
		for (Figure current : figureList) {
			figuresFromList += figureFormatter.formatFigure(current.rental, current.rentalAmount);
		}

		return figuresFromList;
	}

	String generateStatement (final String customerName) {
		String result = statementFormatter.formatStatement(customerName, this.getFigures(), this.getAmountOwed(), this.getEarnedRenterPoints());
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

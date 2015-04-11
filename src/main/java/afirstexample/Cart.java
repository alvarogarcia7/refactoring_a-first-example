package afirstexample;

import java.util.ArrayList;
import java.util.List;

/**
* Created by alvaro on 11/04/15.
*/
public class Cart {
	private final FigureFormatter formatter;
	private final StatementFormatter statementFormatter;
	public double totalAmount = 0d;
	public int frequentRenterPoints = 0;
	private String figures = "";
	private List<Figure> figureList;

	public Cart (final FigureFormatter formatter, final StatementFormatter statementFormatter) {
		this.figureList = new ArrayList<>();
		this.formatter = formatter;
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

	protected double add (final Rental each) {
		double thisAmount = each.getMovie().getPriceType().rented(each.getDaysRented());
		this.totalAmount+=thisAmount;

		this.addRenterPointsFor(each);
		this.addToFigures(formatter.formatFigure(each, thisAmount));
		this.addToFigures(new Figure(each, thisAmount));

		return thisAmount;
	}

	private void addToFigures (final Figure figure) {
		figureList.add(figure);
	}


	private void addToFigures (final String figure) {
		this.figures += figure;
	}

	public String getFigures () {
		return figures;
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

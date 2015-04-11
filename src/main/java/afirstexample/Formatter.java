package afirstexample;

/**
 * Created by alvaro on 11/04/15.
 */
public class Formatter {
	private final FigureFormatter figureFormatter;
	private final StatementFormatter statementFormatter;

	public Formatter (final FigureFormatter figureFormatter, final StatementFormatter statementFormatter) {
		this.figureFormatter = figureFormatter;
		this.statementFormatter = statementFormatter;
	}

	public String formatStatement (final String customerName, final String figures, final String amountOwed, final String earnedRenterPoints) {
		return this.statementFormatter.formatStatement(customerName, figures, amountOwed, earnedRenterPoints);
	}

	public String formatFigure (final Rental rental, final double rentalAmount) {
		return this.figureFormatter.formatFigure(rental, rentalAmount);
	}

	public String formatAmountOwed (final double amountOwed) {
		return "Amount owed is " + String.valueOf(amountOwed)+"\n";
	}

	public String formatRenterPoints (final int renterPoints) {
		return "You earned " + String.valueOf(renterPoints) + " frequent renter points";
	}
}

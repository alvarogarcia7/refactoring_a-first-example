package afirstexample;

/**
 * Created by alvaro on 11/04/15.
 */
public class Formatter {

	public String formatStatement (final String customerName, final String figures, final String amountOwed, final String earnedRenterPoints) {
		String result = "Rental Record for " + customerName + "\n";
		result += figures;
		result += amountOwed;
		result += earnedRenterPoints;
		return result;
	}

	public String formatFigure (final Rental rental, final double rentalAmount) {
		final String fieldSeparator = "\t";
		final String newLineSeparator = "\n";
		return fieldSeparator + rental.getMovie().getTitle() + fieldSeparator + String.valueOf(rentalAmount) + newLineSeparator;
	}

	public String formatAmountOwed (final double amountOwed) {
		return "Amount owed is " + String.valueOf(amountOwed)+"\n";
	}

	public String formatRenterPoints (final int renterPoints) {
		return "You earned " + String.valueOf(renterPoints) + " frequent renter points";
	}
}

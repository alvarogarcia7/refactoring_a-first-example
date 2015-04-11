package afirstexample;

/**
 * Created by alvaro on 11/04/15.
 */
public class Formatter {
	private final String FIELD_SEPARATOR = "\t";
	private final String NEWLINE_SEPARATOR = "\n";

	public String formatStatement (final String customerName, final String figures, final String amountOwed, final String renterPoints) {
		String result = "Rental Record for " + customerName + NEWLINE_SEPARATOR;
		result += figures;
		result += amountOwed;
		result += renterPoints;
		return result;
	}

	public String formatAmountOwed (final double amountOwed) {
		return "Amount owed is " + String.valueOf(amountOwed)+ NEWLINE_SEPARATOR;
	}

	public String formatRenterPoints (final int renterPoints) {
		return "You earned " + String.valueOf(renterPoints) + " frequent renter points";
	}

	public String format (final Figure current) {
		return FIELD_SEPARATOR + current.rental.getMovie().getTitle() + FIELD_SEPARATOR + String.valueOf(current.rentalAmount) + NEWLINE_SEPARATOR;
	}
}

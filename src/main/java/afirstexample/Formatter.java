package afirstexample;

/**
 * Created by alvaro on 11/04/15.
 */
public class Formatter {
	private final String FIELD_SEPARATOR = "\t";
	private final String NEWLINE_SEPARATOR = "\n";

	public String formatStatement (final String customerName, final Figures figures, final String amountOwed, final String renterPoints) {
		String result = "Rental Record for " + customerName + NEWLINE_SEPARATOR;
		result += getFigures(figures);
		result += amountOwed;
		result += renterPoints;
		return result;
	}

	public String getFigures (Figures figures)
	{
		String figureText = "";
		for (Figure current : figures) {
			//TODO AGB feature envy
			figureText += this.format(current);
		}

		return figureText;
	}

	public String formatAmountOwed (final double amountOwed) {
		return "Amount owed is " + String.valueOf(amountOwed)+ NEWLINE_SEPARATOR;
	}

	public String formatRenterPoints (final int renterPoints) {
		return "You earned " + String.valueOf(renterPoints) + " frequent renter points";
	}

	public String format (final Figure current) {
		//TODO AGB feature envy
		return FIELD_SEPARATOR + current.rental.getMovie().getTitle() + FIELD_SEPARATOR + current.rentalAmount + NEWLINE_SEPARATOR;
	}
}

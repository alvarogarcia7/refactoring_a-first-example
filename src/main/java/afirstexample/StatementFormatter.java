package afirstexample;

/**
 * Created by alvaro on 11/04/15.
 */
public class StatementFormatter {
	String formatStatement (final String customerName, final String figures, final String amountOwed, final String earnedRenterPoints) {
		String result = "Rental Record for " + customerName + "\n";
		result += figures;
		result += amountOwed;
		result += earnedRenterPoints;
		return result;
	}
}

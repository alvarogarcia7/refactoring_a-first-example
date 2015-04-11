package afirstexample;

/**
* Created by alvaro on 11/04/15.
*/
public class FigureFormatter {

	public String formatFigure (final Rental each, final double thisAmount) {
		final String fieldSeparator = "\t";
		final String newLineSeparator = "\n";
		return fieldSeparator + each.getMovie().getTitle() + fieldSeparator + String.valueOf(thisAmount) + newLineSeparator;
	}
}

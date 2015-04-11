package afirstexample;

public class Movie {

	private final String _title;
	private final MovieType priceType;

	public Movie (final String title, final MovieType movieType) {
		_title = title;
		priceType = movieType;
	}

	public String getTitle () {
		return _title;
	}

	public MovieType getPriceType () {
		return priceType;
	}

	int getRenterPoints (final int daysRented) {
		int points = 1;
		if ((priceType == MovieType.TYPE_NEW_RELEASE) && daysRented > 1) {
			points++;
		}
		return points;
	}
}

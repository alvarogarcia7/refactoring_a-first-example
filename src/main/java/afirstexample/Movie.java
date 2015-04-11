package afirstexample;

public class Movie {

	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	private final String _title;
	private final MovieType priceType;
	private int _priceCode;

	public Movie (final String title, final int priceCode) {
		this(title, priceCode, MovieType.from(priceCode));
	}

	public Movie (final String title, final int priceCode, final MovieType movieType) {
		_title = title;
		_priceCode = priceCode;
		priceType = movieType;
	}

	public int getPriceCode () {
		return _priceCode;
	}

	public String getTitle () {
		return _title;
	}

	public MovieType getPriceType () {
		return priceType;
	}

	int getRenterPoints (final int daysRented) {
		int points = 1;
		//add bonus for a two day new release rental
		if ((priceType == MovieType.TYPE_NEW_RELEASE) && daysRented > 1) {
			points++;
		}
		return points;
	}
}

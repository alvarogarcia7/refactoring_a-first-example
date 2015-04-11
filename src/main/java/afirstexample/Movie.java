package afirstexample;

public class Movie {

	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	private final String _title;
	private final MovieType priceType;
	private int _priceCode;

	public Movie (final String title, final int priceCode) {
		_title = title;
		_priceCode = priceCode;
		priceType = MovieType.from(priceCode);
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

}

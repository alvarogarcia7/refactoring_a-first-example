package afirstexample;

public class Movie {

	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	private final String _title;
	private int _priceCode;

	public Movie(final String title, final int priceCode) {
		_title = title;
		_priceCode = priceCode;
	}

	public int getPriceCode() {
		return _priceCode;
	}

	public void setPriceCode(final int arg) {
		_priceCode = arg;
	}

	public String getTitle() {
		return _title;
	}

}

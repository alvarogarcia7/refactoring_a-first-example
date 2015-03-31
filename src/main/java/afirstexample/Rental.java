package afirstexample;

public class Rental {

	private final Movie _movie;
	private final int _daysRented;

	public Rental(final Movie movie, final int daysRented) {
		_movie = movie;
		_daysRented = daysRented;
	}

	public int getDaysRented() {
		return _daysRented;
	}

	public Movie getMovie() {
		return _movie;
	}
}

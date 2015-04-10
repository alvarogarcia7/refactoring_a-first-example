package afirstexample;

public class Movie {

	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	public static final MovieType TYPE_NEW_RELEASE = new MovieType() {
		@Override
		public double rented (final int daysRented) {
			return daysRented * 3;
		}
	};
	public static final MovieType TYPE_REGULAR = new MovieType() {
		@Override
		public double rented (final int daysRented) {
			double amount = 2;
			if (daysRented > 2) {
				amount += (daysRented - 2) * 1.5;
			}
			return amount;
		}
	};
	public static final MovieType TYPE_CHILDRENS = new MovieType() {
		@Override
		public double rented (final int daysRented) {
			double amount = 1.5;
			if (daysRented > 3) {
				amount += (daysRented - 3) * 1.5;
			}
			return amount;
		}
	};

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

	public static abstract class MovieType {
		public abstract double rented (final int daysRented);

		public static MovieType from (final int priceCode) {
			switch (priceCode) {
				case Movie.REGULAR:
					return Movie.TYPE_REGULAR;
				case Movie.NEW_RELEASE:
					return Movie.TYPE_NEW_RELEASE;
				case Movie.CHILDRENS:
					return Movie.TYPE_CHILDRENS;
				default:
					throw new UnsupportedOperationException("This Type is not being recognized: " + priceCode);
			}

		}
	}
}

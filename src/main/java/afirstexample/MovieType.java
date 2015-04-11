package afirstexample;

/**
* Created by alvaro on 11/04/15.
*/
public abstract class MovieType {
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

	public abstract double rented (final int daysRented);

	public static MovieType from (final int priceCode) {
		switch (priceCode) {
			case Movie.REGULAR:
				return TYPE_REGULAR;
			case Movie.NEW_RELEASE:
				return TYPE_NEW_RELEASE;
			case Movie.CHILDRENS:
				return TYPE_CHILDRENS;
			default:
				throw new UnsupportedOperationException("This Type is not being recognized: " + priceCode);
		}

	}
}

package afirstexample;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CustomerShould {

	private static final Movie RELEASE_MOVIE = new Movie("release", MovieType.TYPE_NEW_RELEASE);
	private static final Movie CHILDRENS_MOVIE = new Movie("childrens", MovieType.TYPE_CHILDRENS);
	private static final Movie REGULAR_MOVIE = new Movie("regular", MovieType.TYPE_REGULAR);
	final String customerName = "John";
	private Customer customer;

	@Before
	public void setUp () throws Exception {
		customer = newCustomer();
	}

	@Test
	public void owe_zero_when_no_release_rentals() throws Exception {
		assertThat(customer.statement(), is(header(customerName) + owed(0) + earnedFrequentRenter(0)));
	}

	@Test
	public void owe_zero_when_no_childrens_rentals() throws Exception {
		assertThat(customer.statement(), is(header(customerName) + owed(0) + earnedFrequentRenter(0)));
	}

	@Test
	public void cumulate_several_regular_rentals() throws Exception {
		customer.addRental(new Rental(REGULAR_MOVIE, 1));
		customer.addRental(new Rental(REGULAR_MOVIE, 1));
		customer.addRental(new Rental(REGULAR_MOVIE, 2));
		customer.addRental(new Rental(REGULAR_MOVIE, 3));
		assertThat(customer.statement(), is(header(customerName) + line(REGULAR_MOVIE, 2) + line(REGULAR_MOVIE, 2) + line(REGULAR_MOVIE, 2)
				+ line(REGULAR_MOVIE, 3.5) + owed(9.5) + earnedFrequentRenter(4)));
	}

	@Test
	public void cumulate_several_types_of_rentals() throws Exception {
		customer.addRental(new Rental(REGULAR_MOVIE, 1));
		customer.addRental(new Rental(CHILDRENS_MOVIE, 1));
		assertThat(customer.statement(), is(header(customerName) + "\tregular\t" + (2.0) + "\n" + "\tchildrens\t" + (1.5) + "\n"
				+ owed(3.5) + earnedFrequentRenter(2)));
	}

	@Test
	public void constant_amount_for_regular_movies_during_the_first_two_days() throws Exception {
		for (int days = 1; days <= 2; days++) {
			final Customer customer = newCustomer();
			customer.addRental(new Rental(REGULAR_MOVIE, days));
			assertThat("failed when days = " + days, customer.statement(), is(header(customerName) + "\tregular\t" + (2.0) + "\n"
					+ owed(2.0) + earnedFrequentRenter(1)));
		}
	}

	@Test
	public void flat_rate_for_regular_movies_after_the_second_day() throws Exception {
		for (int days = 2; days <= 100; days++) {
			final Customer customer = newCustomer();
			customer.addRental(new Rental(REGULAR_MOVIE, days));

			final double subtotal = 2 + (days - 2) * 1.5;
			final double total = subtotal;

			assertThat(customer.statement(), is(header(customerName) + "\tregular\t" + (subtotal) + "\n" + owed(total)
					+ earnedFrequentRenter(1)));
		}
	}

	@Test
	public void flat_rate_for_childrens_during_the_first_three_days() throws Exception {
		for (int days = 1; days <= 3; days++) {
			final Customer customer = newCustomer();
			customer.addRental(new Rental(CHILDRENS_MOVIE, days));
			assertThat("failed when days = " + days, customer.statement(), is(header(customerName) + "\tchildrens\t" + (1.5) + "\n"
					+ owed(1.5) + earnedFrequentRenter(1)));
		}
	}

	@Test
	public void flat_rate_for_childrens_after_the_third_day() throws Exception {
		for (int days = 3; days <= 100; days++) {
			final Customer customer = newCustomer();
			customer.addRental(new Rental(CHILDRENS_MOVIE, days));

			final double subtotal = 1.5 + (days - 3) * 1.5;
			final double total = subtotal;

			assertThat(customer.statement(), is(header(customerName) + "\tchildrens\t" + (subtotal) + "\n" + owed(total)
					+ earnedFrequentRenter(1)));
		}
	}

	@Test
	public void award_two_frequent_renter_points_when_rented_a_release_for_more_than_one_day() throws Exception {
		for (int days = 2; days < 1000; days++) {
			final Customer customer = newCustomer();
			customer.addRental(new Rental(RELEASE_MOVIE, days));
			assertThat("failed when days = " + days, customer.statement(), is(header(customerName) + "\trelease\t" + (days * 3.0) + "\n"
					+ owed(days * 3.0) + earnedFrequentRenter(2)));
		}
	}

	@Test
	public void owe_three_for_the_first_day_when_rented_a_release() throws Exception {
		final int days = 1;
		customer.addRental(new Rental(RELEASE_MOVIE, days));
		assertThat("failed when days = " + days, customer.statement(), is(header(customerName) + "\trelease\t" + (days * 3.0) + "\n"
				+ owed(days * 3.0)
				+ earnedFrequentRenter(1)));
	}


	private String earnedFrequentRenter(final int points) {
		return "You earned " + points + " frequent renter points";
	}

	private String owed(final double amount) {
		return "Amount owed is " + amount + "\n";
	}

	private String header(final String customerName) {
		return "Rental Record for " + customerName + "\n";
	}

	private String line(final Movie movie, final double price) {
		return "\t" + movie.getTitle() + "\t" + (price) + "\n";
	}

	private Customer newCustomer () {
		return new Customer(customerName, new Cart(new Figures(), new Formatter()));
	}

}

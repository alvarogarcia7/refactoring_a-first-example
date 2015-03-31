package afirstexample;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CustomerShould {

	private static final Movie RELEASE_MOVIE = new Movie("release", Movie.NEW_RELEASE);
	private static final Movie CHILDRENS_MOVIE = new Movie("childrens", Movie.CHILDRENS);
	private static final Movie REGULAR_MOVIE = new Movie("regular", Movie.REGULAR);
	final String customerName = "John";

	@Test
	public void owe_zero_when_no_release_rentals() throws Exception {
		final Customer customer = new Customer(customerName);
		assertThat(customer.statement(), is(header(customerName) + owed(0) + earnedFrequentRenter(0)));
	}

	@Test
	public void owe_zero_when_no_childrens_rentals() throws Exception {
		final Customer customer = new Customer(customerName);
		assertThat(customer.statement(), is(header(customerName) + owed(0) + earnedFrequentRenter(0)));
	}

	@Test
	public void cumulate_several_regular_rentals() throws Exception {
		final Customer customer = new Customer(customerName);
		customer.addRental(new Rental(REGULAR_MOVIE, 1));
		customer.addRental(new Rental(REGULAR_MOVIE, 1));
		customer.addRental(new Rental(REGULAR_MOVIE, 2));
		assertThat(customer.statement(), is(header(customerName) + "\tregular\t" + (2.0) + "\n" + "\tregular\t" + (2.0) + "\n"
				+ "\tregular\t" + (2.0) + "\n" + owed(6.0)
				+ earnedFrequentRenter(3)));
	}

	@Test
	public void constant_amount_for_regular_movies_during_the_first_two_days() throws Exception {
		for (int days = 1; days <= 2; days++) {
			final Customer customer = new Customer(customerName);
			customer.addRental(new Rental(REGULAR_MOVIE, days));
			assertThat("failed when days = " + days, customer.statement(), is(header(customerName) + "\tregular\t" + (2.0) + "\n"
					+ owed(2.0) + earnedFrequentRenter(1)));
		}
	}

	@Test
	public void flat_rate_for_regular_movies_after_the_second_day() throws Exception {
		for (int days = 2; days <= 100; days++) {
			final Customer customer = new Customer(customerName);
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
			final Customer customer = new Customer(customerName);
			customer.addRental(new Rental(CHILDRENS_MOVIE, days));
			assertThat("failed when days = " + days, customer.statement(), is(header(customerName) + "\tchildrens\t" + (1.5) + "\n"
					+ owed(1.5) + earnedFrequentRenter(1)));
		}
	}

	@Test
	public void flat_rate_for_childrens_after_the_third_day() throws Exception {
		for (int days = 3; days <= 100; days++) {
			final Customer customer = new Customer(customerName);
			customer.addRental(new Rental(CHILDRENS_MOVIE, days));

			final double subtotal = 1.5 + (days - 3) * 1.5;
			final double total = subtotal;

			assertThat(customer.statement(), is(header(customerName) + "\tchildrens\t" + (subtotal) + "\n" + owed(total)
					+ earnedFrequentRenter(1)));
		}
	}

	@Test
	public void owe_three_per_each_day_when_rented_a_release_for_more_than_1_days() throws Exception {
		for (int days = 1; days < 1000; days++) {
			final Customer customer = new Customer(customerName);
			customer.addRental(new Rental(RELEASE_MOVIE, days));
			assertThat("failed when days = " + days, customer.statement(), is(header(customerName) + "\trelease\t" + (days * 3.0) + "\n"
					+ owed(days * 3.0)
					+ earnedFrequentRenter(Math.min(days, 2))));
		}
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

}

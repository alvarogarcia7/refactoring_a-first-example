package afirstexample;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CustomerShould {

	private static final Movie RELEASE_MOVIE = new Movie("release", Movie.NEW_RELEASE);
	final String customerName = "John";

	@Test
	public void owe_zero_when_no_rentals() throws Exception {
		final Customer customer = new Customer(customerName);
		assertThat(customer.statement(), is(header(customerName) + owed(0) + earnedFrequentRenter(0)));
	}

	@Test
	public void owe_3_when_rented_a_release_for_a_day() throws Exception {
		final Customer customer = new Customer(customerName);
		customer.addRental(new Rental(RELEASE_MOVIE, 1));
		assertThat(customer.statement(),
				is(header(customerName) + "\trelease\t3.0\n" + owed(3.0) + earnedFrequentRenter(1)));
	}

	@Test
	public void owe_6_when_rented_a_release_for_two_day() throws Exception {
		final Customer customer = new Customer(customerName);
		customer.addRental(new Rental(RELEASE_MOVIE, 2));
		assertThat(customer.statement(), is(header(customerName) + "\trelease\t6.0\n" + owed(6.0) + earnedFrequentRenter(2)));
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

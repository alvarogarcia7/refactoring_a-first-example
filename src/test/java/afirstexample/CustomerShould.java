package afirstexample;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CustomerShould {

	private static final Movie RELEASE_MOVIE = new Movie("release", Movie.NEW_RELEASE);

	@Test
	public void owe_zero_when_no_rentals() throws Exception {
		final Customer customer = new Customer("John");
		assertThat(customer.statement(), is("Rental Record for John\nAmount owed is 0.0\nYou earned 0 frequent renter points"));
	}

	@Test
	public void owe_3_when_rented_a_release_for_a_day() throws Exception {
		final Customer customer = new Customer("Alice");
		customer.addRental(new Rental(RELEASE_MOVIE, 1));
		assertThat(customer.statement(),
				is(header("Alice") + "\trelease\t3.0\n" + owed(3.0) + "You earned 1 frequent renter points"));
	}

	private String owed(final double amount) {
		return "Amount owed is " + amount + "\n";
	}

	private String header(final String customerName) {
		return "Rental Record for " + customerName + "\n";
	}

}

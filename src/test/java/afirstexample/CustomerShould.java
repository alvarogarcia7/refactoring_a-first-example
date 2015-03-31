package afirstexample;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CustomerShould {

	@Test
	public void owe_zero_when_no_rentals() throws Exception {
		final Customer customer = new Customer("John");
		assertThat(customer.statement(), is("Rental Record for John\nAmount owed is 0.0\nYou earned 0 frequent renter points"));
	}

}

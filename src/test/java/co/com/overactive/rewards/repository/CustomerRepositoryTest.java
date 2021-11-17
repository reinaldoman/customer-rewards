package co.com.overactive.rewards.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.overactive.rewards.model.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerRepository customers;

	@Test
	public void testFindByName() {
		Customer customer = new Customer("first last");
		entityManager.persist(customer);

		List<Customer> findByName = customers.findByName(customer.getName());

		assertThat(findByName).extracting(Customer::getName).containsOnly(customer.getName());
	}
}

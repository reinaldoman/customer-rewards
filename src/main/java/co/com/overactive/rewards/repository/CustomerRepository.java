package co.com.overactive.rewards.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.com.overactive.rewards.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByName(String name);
}

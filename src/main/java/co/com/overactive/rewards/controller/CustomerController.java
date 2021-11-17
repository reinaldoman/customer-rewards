package co.com.overactive.rewards.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.overactive.rewards.model.Customer;
import co.com.overactive.rewards.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerRepository repository;

	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		log.info("Get all Customers...");

		List<Customer> customers = new ArrayList<>();
		repository.findAll().forEach(customers::add);

		return customers;
	}

	@PostMapping("/customer")
	public Customer postCustomer(@RequestBody Customer customer) {

		Customer _customer = repository.save(new Customer(customer.getName()));
		return _customer;
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {
		log.info("Delete Customer with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@GetMapping("customers/name/{name}")
	public List<Customer> findByName(@PathVariable String name) {

		List<Customer> customers = repository.findByName(name);
		return customers;
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		log.info("Update Customer with ID = " + id + "...");

		Optional<Customer> customerData = repository.findById(id);

		if (customerData.isPresent()) {
			Customer _customer = customerData.get();
			_customer.setName(customer.getName());
			return new ResponseEntity<>(repository.save(_customer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

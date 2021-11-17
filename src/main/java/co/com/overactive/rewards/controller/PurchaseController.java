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

import co.com.overactive.rewards.model.Purchase;
import co.com.overactive.rewards.repository.PurchaseRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PurchaseController {

	private static final Logger log = LoggerFactory.getLogger(PurchaseController.class);
	
	@Autowired
	PurchaseRepository repository;

	@GetMapping("/purchases")
	public List<Purchase> getAllPurchases() {
		log.info("Get all Purchases...");

		List<Purchase> purchases = new ArrayList<>();
		repository.findAll().forEach(purchases::add);

		return purchases;
	}

	@PostMapping("/purchase")
	public Purchase postPurchase(@RequestBody Purchase purchase) {

		Purchase _purchase = repository.save(new Purchase(purchase.getDescription(), purchase.getValue(), purchase.getCustomerId(), purchase.getDate()));
		return _purchase;
	}

	@DeleteMapping("/purchase/{id}")
	public ResponseEntity<String> deletePurchase(@PathVariable("id") long id) {
		log.info("Delete Purchase with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Purchase has been deleted!", HttpStatus.OK);
	}

	@GetMapping("purchases/customer/{customerId}")
	public List<Purchase> findByCustomerId(@PathVariable long customerId) {

		List<Purchase> purchases = repository.findByCustomerId(customerId);
		return purchases;
	}
	
	@GetMapping("purchases/customer/points/{customerId}")
	public List<Purchase> findMonthlyPointsByCustomerId(@PathVariable long customerId) {

		List<Purchase> purchases = repository.findMonthlyPointsByCustomerId(customerId);
		return purchases;
	}

	@PutMapping("/purchase/{id}")
	public ResponseEntity<Purchase> updatePurchase(@PathVariable("id") long id, @RequestBody Purchase purchase) {
		log.info("Update Purchase with ID = " + id + "...");

		Optional<Purchase> purchaseData = repository.findById(id);

		if (purchaseData.isPresent()) {
			Purchase _purchase = purchaseData.get();
			_purchase.setDescription(purchase.getDescription());
			_purchase.setValue(purchase.getValue());
			return new ResponseEntity<>(repository.save(_purchase), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

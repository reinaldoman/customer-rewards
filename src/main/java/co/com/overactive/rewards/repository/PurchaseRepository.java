package co.com.overactive.rewards.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.com.overactive.rewards.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
	List<Purchase> findByDescription(String description);
}

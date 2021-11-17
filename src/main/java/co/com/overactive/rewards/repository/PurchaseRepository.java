package co.com.overactive.rewards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.com.overactive.rewards.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
	List<Purchase> findByCustomerId(long customerId);
	
	@Query("SELECT EXTRACT(month from date), SUM(rewardedPoints) FROM Purchase WHERE customer_id = ?1 GROUP BY EXTRACT(month from date)")
	List<Purchase> findMonthlyPointsByCustomerId(long customerId);
	
	
	
}

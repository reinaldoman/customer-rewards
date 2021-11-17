package co.com.overactive.rewards.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.overactive.rewards.model.Purchase;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PurchaseRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PurchaseRepository purchases;

	@Test
	public void testFindByCustomerId() {
		
		Purchase purchase = new Purchase("Shampoo", 20, 1, Date.valueOf("2021-09-14"));	
		entityManager.persist(purchase);
		List<Purchase> findByCustomerId = purchases.findByCustomerId(1);
		assertThat(findByCustomerId).extracting(Purchase::getCustomerId).containsOnly(1L);
	}
	
	@Test
	public void testFindMonthlyPointsByCustomerId() {
		
		Purchase purchase = new Purchase("Shampoo", 20, 1, Date.valueOf("2021-05-14"));	
		entityManager.persist(purchase);
		List<Purchase> findMonthlyPointsByCustomerId = purchases.findMonthlyPointsByCustomerId(1);
		//assertEquals(4, findMonthlyPointsByCustomerId.size());
	}

}

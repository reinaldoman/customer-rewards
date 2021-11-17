package co.com.overactive.rewards;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.com.overactive.rewards.model.Customer;
import co.com.overactive.rewards.model.Purchase;
import co.com.overactive.rewards.repository.CustomerRepository;
import co.com.overactive.rewards.repository.PurchaseRepository;

@SpringBootApplication
public class App {
	
	private static final Logger log = LoggerFactory.getLogger(App.class);
	
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
    @Bean
	public CommandLineRunner dataSet(CustomerRepository customerRepository, PurchaseRepository purchaseRepository) {
		return (args) -> {
			// save a few customers
			customerRepository.save(new Customer("Reinaldo Otalvaro"));
			customerRepository.save(new Customer("John Doe"));
			customerRepository.save(new Customer("Roger Waters"));
			customerRepository.save(new Customer("David Gilmour"));
			customerRepository.save(new Customer("Arturo Calle"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : customerRepository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");
			
			// save a few purchases
			//Customer1
			purchaseRepository.save(new Purchase("Shoes", 120, 1, Date.valueOf("2021-11-09")));
			purchaseRepository.save(new Purchase("Pants", 200, 1, Date.valueOf("2021-11-17")));
			purchaseRepository.save(new Purchase("T-shirt", 70, 1, Date.valueOf("2021-10-09")));
			purchaseRepository.save(new Purchase("Socks", 30, 1, Date.valueOf("2021-09-21")));
			purchaseRepository.save(new Purchase("Glasses", 300, 1, Date.valueOf("2021-09-15")));
			
			//Customer2
			purchaseRepository.save(new Purchase("Table", 321, 2, Date.valueOf("2021-10-09")));
			purchaseRepository.save(new Purchase("Chair", 102, 2, Date.valueOf("2021-10-17")));
			purchaseRepository.save(new Purchase("Closet", 632, 2, Date.valueOf("2021-10-09")));
			purchaseRepository.save(new Purchase("Socks", 30, 2, Date.valueOf("2021-11-21")));
			purchaseRepository.save(new Purchase("Glasses", 300, 2, Date.valueOf("2021-11-15")));
			
			//Customer3
			purchaseRepository.save(new Purchase("Shampoo", 20, 3, Date.valueOf("2021-09-14")));
			purchaseRepository.save(new Purchase("Airplane ticket", 150, 3, Date.valueOf("2021-09-05")));
			purchaseRepository.save(new Purchase("Tooth paste", 5, 3, Date.valueOf("2021-09-06")));
			purchaseRepository.save(new Purchase("Books", 215, 3, Date.valueOf("2021-11-07")));
			purchaseRepository.save(new Purchase("Services", 700, 3, Date.valueOf("2021-11-08")));
			
			//Customer4
			purchaseRepository.save(new Purchase("Product", 152, 4, Date.valueOf("2021-11-14")));
			purchaseRepository.save(new Purchase("Subscription", 600, 4, Date.valueOf("2021-11-05")));
			
			//Customer5
			purchaseRepository.save(new Purchase("Desk", 231, 5, Date.valueOf("2021-11-17")));

			// fetch all purchases
			log.info("purchases found with findAll():");
			log.info("-------------------------------");
			for (Purchase purchase : purchaseRepository.findAll()) {
				log.info(purchase.toString());
			}
			log.info("");
		};
	}
    
}
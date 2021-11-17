package co.com.overactive.rewards.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "description")
	private String description;
	
	@Column(name = "value")
	private double value;
	
	@Column(name = "customerId")
	private long customerId;
	
	@Column(name = "rewardedPoints")
	private long rewardedPoints;
	
	@Column(name = "date")
	private Date date;
	
	public Purchase() {
	}

	public Purchase(String description, double value, long customerId, Date date) {
		this.description = description;
		this.value = value;
		this.customerId = customerId;
		this.date = date;
		this.rewardedPoints = calculatePoints();
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	public long getId() {
		return id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	
	//TODO: Move this values to config in properties file.
	private long calculatePoints() {
		long points = 0;
		long totalPoints = 0;
		if(this.value > 100)
			points = (long)(value - 100) * 2;
		if(this.value > 50)
			totalPoints = (long)(value - 50);
		totalPoints = totalPoints + points;
		return totalPoints;
	}
	
	public long getRewardedPoints() {
		return rewardedPoints;
	}

	public void setRewardedPoints(long rewardedPoints) {
		this.rewardedPoints = rewardedPoints;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Purchase [id=" + id + ", name=" + description + ", value=" + value + "]";
	}
}

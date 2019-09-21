package forms;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

public class FinderForm {
	
	//Attributes=====================================================================================
	
	private String destinationCity;
	private Double minimumPrice;
	private Double maximumPrice;
	private String keyword;
	
	//Getters & setters================================================================================
	
	@NotBlank
	@SafeHtml
	public String getDestinationCity() {
		return destinationCity;
	}
	
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	
	@Digits(integer=99,fraction=2)
	public Double getMinimumPrice() {
		return minimumPrice;
	}
	
	public void setMinimumPrice(Double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	
	@Digits(integer=99,fraction=2)
	public Double getMaximumPrice() {
		return maximumPrice;
	}
	
	public void setMaximumPrice(Double maximumPrice) {
		this.maximumPrice = maximumPrice;
	}
	
	@NotBlank
	@SafeHtml
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}

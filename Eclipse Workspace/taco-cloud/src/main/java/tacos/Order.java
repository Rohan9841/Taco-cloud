package tacos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

//Automatically defines the basic getter, setter, equal, hash and tostring() method
@Data
public class Order {

	private Long id;
	private Date placedAt;
	//javax validation to make sure that name field is not blank
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message  = "Street is required")
	private String street;
	
	@NotBlank(message = "City is required")
	private String city;
	
	@NotBlank(message = "State is required")
	private String state;
	
	@NotBlank(message = "zip code is required")
	private String zip;
	
	//passes Luhn's algorithm check to validate the entered credit card number
	@CreditCardNumber(message = "Not a valid credit card number")
	private String ccNumber;
	
	//passes regex to make sure that user inputs the value in mm/yy format
	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	private String ccExpiration;
	
	//ensures that the value contains exactly three numeric digits
	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	private String ccCVV;
	
	/*
	 * This list of tacos will be used to link order id and tacos related to that
	 * order in jdbcOrderRepository
	 */
	private List<Taco> tacos = new ArrayList<>();
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}
}

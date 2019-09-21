package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.CreditCard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class CreditCardServiceTest extends AbstractTest{
	
	// Service under test ============================================================================

	@Autowired
	private CreditCardService creditCardService;
	
	// Tests =========================================================================================
	
	/**
	* ################################################################
	*
	* TEST POSITIVOS
	*
	* #################################################################
	*/
	
	@Test
	public void testCreate() {
		System.out.println("----------------------------Create------------------------------");		

		authenticate("sponsor1");
	
		CreditCard result;
		
		result = creditCardService.create();
		
		System.out.println("Brand Name: " + result.getBrandName());
		System.out.println("Credit Card Number: " + result.getNumber());
		System.out.println("Holder Name: " + result.getHolderName());
		System.out.println("Expiration Month: " + result.getExpirationMonth());
		System.out.println("Expiration Year: " + result.getExpirationYear());
		System.out.println("CVV: " + result.getCvv());

		authenticate(null);

		System.out.println("----------------------------------------------------------------"); 
	}
	
	@Test
	public void testSave() {
		System.out.println("----------------------------Save------------------------------");		

		authenticate("sponsor1");
	
		CreditCard result;
		
		result = creditCardService.create();
		
		result.setBrandName("VISA");
		result.setHolderName("Holder NAme");
		result.setNumber("4771823145360438");
		result.setCvv(278);
		result.setExpirationMonth(8);
		result.setExpirationYear(2018);
		
		System.out.println("Brand Name: " + result.getBrandName());
		System.out.println("Credit Card Number: " + result.getNumber());
		System.out.println("Holder Name: " + result.getHolderName());
		System.out.println("Expiration Month: " + result.getExpirationMonth());
		System.out.println("Expiration Year: " + result.getExpirationYear());
		System.out.println("CVV: " + result.getCvv());

		authenticate(null);

		System.out.println("----------------------------------------------------------------"); 
	}
	
	/**
	* ################################################################
	*
	* TEST NEGATIVOS
	*
	* #################################################################
	*/
	
	@Test
	public void testCreateNegative() {
		System.out.println("----------------------------Create Negative------------------------------");		

		try{
			authenticate("user1");
			
			CreditCard result;
			
			result = creditCardService.create();
			
			System.out.println("Brand Name: " + result.getBrandName());
			System.out.println("Credit Card Number: " + result.getNumber());
			System.out.println("Holder Name: " + result.getHolderName());
			System.out.println("Expiration Month: " + result.getExpirationMonth());
			System.out.println("Expiration Year: " + result.getExpirationYear());
			System.out.println("CVV: " + result.getCvv());

			authenticate(null);

		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario registrado no puede realizar esta operacion"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@Test
	public void testSaveNegative() {
		System.out.println("----------------------------Save Negative------------------------------");		

		try{
			authenticate("user1");
			
			CreditCard result;
			
			result = creditCardService.create();
			
			result.setBrandName("VISA");
			result.setHolderName("Holder NAme");
			result.setNumber("4771823145360438");
			result.setCvv(278);
			result.setExpirationMonth(8);
			result.setExpirationYear(2018);
			
			System.out.println("Brand Name: " + result.getBrandName());
			System.out.println("Credit Card Number: " + result.getNumber());
			System.out.println("Holder Name: " + result.getHolderName());
			System.out.println("Expiration Month: " + result.getExpirationMonth());
			System.out.println("Expiration Year: " + result.getExpirationYear());
			System.out.println("CVV: " + result.getCvv());

			authenticate(null);
				
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario registrado no puede realizar esta operacion"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
}

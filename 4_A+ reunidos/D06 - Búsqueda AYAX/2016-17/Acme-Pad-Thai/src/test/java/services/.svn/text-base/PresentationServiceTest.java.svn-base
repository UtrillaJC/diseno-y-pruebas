package services;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;
import domain.Presentation;
import domain.MasterClass;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class PresentationServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private PresentationService presentationService;
	
	@Autowired
	private MasterClassService masterClassService;
		
	// Tests =========================================================================================
	
	/**
	* ################################################################
	*
	* TEST POSITIVOS
	*
	* #################################################################
	*/
	
	@Test
	public void testCreate(){
		System.out.println("-----------------------------Create-----------------------------");
		authenticate("cook1");
		Presentation presentation;
		
		MasterClass masterClass;
		masterClass = masterClassService.findOne(42);
		
		presentation = presentationService.create(masterClass);
		System.out.println("Title: " + presentation.getTitle());
		System.out.println("AbstractText: " + presentation.getAbstractText());
		System.out.println("Attachments: " + presentation.getAttachments());
		System.out.println("Path: " + presentation.getPath());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testSave()throws MalformedURLException{
		System.out.println("-----------------------------Save-----------------------------");
		authenticate("cook1");
		Presentation presentation;
		MasterClass masterClass;
		URL url;
		
		masterClass = masterClassService.findOne(42);
		presentation = presentationService.create(masterClass);
		url = new URL("https://attachmentstest.com");
		
		presentation.setTitle("Presentation Test");
		presentation.setAbstractText("AbstractText Test");
		presentation.getAttachments().add(url);
		presentation.setPath("https://pathtest.com");
				
		presentation = presentationService.save(presentation);
		
		System.out.println("Presentation: " + presentation.getTitle());
		System.out.println("AbstractText: " + presentation.getAbstractText());
		System.out.println("Attachments: " + presentation.getAttachments());
		System.out.println("Path: " + presentation.getPath());

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
	public void testCreateNegative(){
		System.out.println("-----------------------------Create Negative-----------------------------");
		try{
			authenticate("nutritionist1");
			Presentation presentation;
			
			MasterClass masterClass;
			masterClass = masterClassService.findOne(38);
			
			presentation = presentationService.create(masterClass);
			System.out.println("Title: " + presentation.getTitle());
			System.out.println("AbstractText: " + presentation.getAbstractText());
			System.out.println("Attachments: " + presentation.getAttachments());
			System.out.println("Path: " + presentation.getPath());
			
			authenticate(null);
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("El nutricionista logueado no puede realizar esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@Test
	public void testSaveNegative() throws MalformedURLException{
		System.out.println("-----------------------------Save Negative-----------------------------");
		try{
			authenticate("nutritionist1");
			Presentation presentation;
			MasterClass masterClass;
			URL url;
			
			masterClass = masterClassService.findOne(42);
			presentation = presentationService.create(masterClass);
			url = new URL("https://attachmentstest.com");
			
			presentation.setTitle("Presentation Test");
			presentation.setAbstractText("AbstractText Test");
			presentation.getAttachments().add(url);
			presentation.setPath("https://pathtest.com");
					
			presentation = presentationService.save(presentation);
			
			System.out.println("Presentation: " + presentation.getTitle());
			System.out.println("AbstractText: " + presentation.getAbstractText());
			System.out.println("Attachments: " + presentation.getAttachments());
			System.out.println("Path: " + presentation.getPath());
	
			authenticate(null);
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("El usuario nutricionista no puede realizar esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
}
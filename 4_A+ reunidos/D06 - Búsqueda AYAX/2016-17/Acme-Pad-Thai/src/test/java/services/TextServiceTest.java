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
import domain.MasterClass;
import domain.Text;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class TextServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private TextService textService;
	
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
		Text text;
		
		MasterClass masterClass;
		masterClass = masterClassService.findOne(38);
		
		text = textService.create(masterClass);
		System.out.println("Title: " + text.getTitle());
		System.out.println("AbstractText: " + text.getAbstractText());
		System.out.println("Attachments: " + text.getAttachments());
		System.out.println("Body: " + text.getBody());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testSave() throws MalformedURLException{
		System.out.println("-----------------------------Save-----------------------------");
		authenticate("cook1");
		Text text;
		MasterClass masterClass;
		URL url;
		
		masterClass = masterClassService.findOne(38);
		text = textService.create(masterClass);
		url = new URL("https://attachmentstest.com");
		
		text.setTitle("Video Test");
		text.setAbstractText("AbstractText Test");
		text.getAttachments().add(url);
		text.setBody("Body Test");
				
		text = textService.save(text);
		
		System.out.println("Text: " + text.getTitle());
		System.out.println("AbstractText: " + text.getAbstractText());
		System.out.println("Attachments: " + text.getAttachments());
		System.out.println("Body: " + text.getBody());

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
			Text text;
			
			MasterClass masterClass;
			masterClass = masterClassService.findOne(38);
			
			text = textService.create(masterClass);
			System.out.println("Title: " + text.getTitle());
			System.out.println("AbstractText: " + text.getAbstractText());
			System.out.println("Attachments: " + text.getAttachments());
			System.out.println("Body: " + text.getBody());
			
			authenticate(null);
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("El nutricionista logueado no puede realizar esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@Test
	public void testSaveNegative()throws MalformedURLException{
		System.out.println("-----------------------------Save Negative-----------------------------");
		try{
			authenticate("nutritionist1");
			Text text;
			MasterClass masterClass;
			URL url;
			
			
			masterClass = masterClassService.findOne(38);
			text = textService.create(masterClass);
			url = new URL("https://attachmentstest.com");
			
			text.setTitle("Video Test");
			text.setAbstractText("AbstractText Test");
			text.getAttachments().add(url);
			text.setBody("Body Test");
					
			text = textService.save(text);
			
			System.out.println("Text: " + text.getTitle());
			System.out.println("AbstractText: " + text.getAbstractText());
			System.out.println("Attachments: " + text.getAttachments());
			System.out.println("Identifier: " + text.getBody());
	
			authenticate(null);
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("El nutricionista logueado no puede realizar esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
}
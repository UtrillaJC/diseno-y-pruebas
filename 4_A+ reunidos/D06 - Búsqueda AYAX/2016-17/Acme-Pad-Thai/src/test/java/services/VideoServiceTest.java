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
import domain.Video;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class VideoServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private MasterClassService masterClassService;
		
	// Tests =========================================================================================
	
	/**
	* ################################################################
	*
	* TEST POSITIVOS
	*
	* ################################################################
	*/
	
	@Test
	public void testCreate(){
		System.out.println("-----------------------------Create-----------------------------");
		authenticate("cook1");
		Video video;
		
		MasterClass masterClass;
		masterClass = masterClassService.findOne(40);
		
		video = videoService.create(masterClass);
		System.out.println("Title: " + video.getTitle());
		System.out.println("AbstractText: " + video.getAbstractText());
		System.out.println("Attachments: " + video.getAttachments());
		System.out.println("Identifier: " + video.getIdentifier());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testSave() throws MalformedURLException{
		System.out.println("-----------------------------Save-----------------------------");
		authenticate("cook1");
		Video video;
		MasterClass masterClass;
		URL url;
		
		masterClass = masterClassService.findOne(40);
		video = videoService.create(masterClass);
		url = new URL("https://attachmentstest.com");
		
		video.setTitle("Video Test");
		video.setAbstractText("AbstractText Test");
		video.getAttachments().add(url);
		video.setIdentifier("https://identifiertest.com");
				
		video = videoService.save(video);
		
		System.out.println("Video: " + video.getTitle());
		System.out.println("AbstractText: " + video.getAbstractText());
		System.out.println("Attachments: " + video.getAttachments());
		System.out.println("Identifier: " + video.getIdentifier());

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
		try{
			System.out.println("-----------------------------Create Negative-----------------------------");
			authenticate("cook1");
			Video video;
			
			MasterClass masterClass;
			masterClass = masterClassService.findOne(40);
			
			video = videoService.create(masterClass);
			System.out.println("Title: " + video.getTitle());
			System.out.println("AbstractText: " + video.getAbstractText());
			System.out.println("Attachments: " + video.getAttachments());
			System.out.println("Identifier: " + video.getIdentifier());
			
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
		try{
			System.out.println("-----------------------------Save Negative-----------------------------");
			authenticate("nutritionist1");
			Video video;
			MasterClass masterClass;
			URL url;
			
			masterClass = masterClassService.findOne(40);
			video = videoService.create(masterClass);
			url = new URL("https://attachmentstest.com");
			
			video.setTitle("Video Test");
			video.setAbstractText("AbstractText Test");
			video.getAttachments().add(url);
			video.setIdentifier("https://identifiertest.com");
					
			video = videoService.save(video);
			
			System.out.println("Video: " + video.getTitle());
			System.out.println("AbstractText: " + video.getAbstractText());
			System.out.println("Attachments: " + video.getAttachments());
			System.out.println("Identifier: " + video.getIdentifier());
	
			authenticate(null);
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("El nutricionista logueado no puede realizar esta operación"); 
			}
		finally{
			System.out.println("----------------------------------------------------------------"); 
			}
	}
	
}
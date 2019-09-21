package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;
import domain.Curriculum;
import domain.Nutritionist;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class CurriculumServiceTest extends AbstractTest{
	
	// Service under test ============================================================================
	
	@Autowired
	private CurriculumService curriculumService;
		
	@Autowired
	private NutritionistService nutritionistService;
	
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
		authenticate("nutritionist1");
		
		Curriculum curriculum;
		Nutritionist nutritionist;
		nutritionist = nutritionistService.findOne(18);
		
		curriculum = curriculumService.create(nutritionist);
		
		System.out.println("Photo: " + curriculum.getPhoto());
		System.out.println("EducationSection: " + curriculum.getEducationSection());
		System.out.println("ExperienceSection: " + curriculum.getExperienceSection());
		System.out.println("HobbiesSection: " + curriculum.getHobbiesSection());
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testSave() {
		System.out.println("----------------------------Save------------------------------");
		authenticate("nutritionist1");
		
		Curriculum result;
		Nutritionist nutritionist;
		
		nutritionist = nutritionistService.findOne(18);
		result = curriculumService.create(nutritionist);
		
		result.setPhoto("https://phototest.com");
		result.setEducationSection("EducationSection Test");
		result.setExperienceSection("ExperienceSection Test");
		result.setHobbiesSection("HobbiesSection Test");
		
		curriculumService.save(result);
		
		System.out.println("Photo: " + result.getPhoto());
		System.out.println("EducationSection: " + result.getEducationSection());
		System.out.println("ExperienceSection: " + result.getExperienceSection());
		System.out.println("HobbiesSection: " + result.getHobbiesSection());
		
		authenticate(null);
		
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testDelete() {
		System.out.println("----------------------------Delete------------------------------");
		authenticate("nutritionist1");
		
		Curriculum result;
		Nutritionist nutritionist;
		
		nutritionist = nutritionistService.findOne(18);
		result = curriculumService.create(nutritionist);
		
		result.setPhoto("https://phototest.com");
		result.setEducationSection("EducationSection Test");
		result.setExperienceSection("ExperienceSection Test");
		result.setHobbiesSection("HobbiesSection Test");
		
		curriculumService.save(result);
		
		System.out.println("Photo: " + result.getPhoto());
		System.out.println("EducationSection: " + result.getEducationSection());
		System.out.println("ExperienceSection: " + result.getExperienceSection());
		System.out.println("HobbiesSection: " + result.getHobbiesSection());
		
		curriculumService.delete(result);
		
		authenticate(null);
		System.out.println("----------------------------------------------------------------");
	}
	
	@Test
	public void testfindAllByNutritionist() {
		System.out.println("----------------------------Find All By Nutritionist------------------------------");
		
		Collection<Curriculum> result;
		Nutritionist nutritionist;
		
		nutritionist = nutritionistService.findOne(18);
		
		result = curriculumService.findAllByNutritionist(nutritionist);
		
		for(Curriculum c : result){
			System.out.println("Title: " + c.getPhoto());
			System.out.println("Moment Opening: " + c.getEducationSection());
			System.out.println("Moment Closing: " + c.getExperienceSection());
			System.out.println("Winners: " + c.getHobbiesSection());
		}
		
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
			
			Curriculum curriculum;
			Nutritionist nutritionist;
			nutritionist = nutritionistService.findOne(22);
			
			curriculum = curriculumService.create(nutritionist);
			System.out.println("Photo: " + curriculum.getPhoto());
			System.out.println("EducationSection: " + curriculum.getEducationSection());
			System.out.println("ExperienceSection: " + curriculum.getExperienceSection());
			System.out.println("HobbiesSection: " + curriculum.getHobbiesSection());
			
			authenticate(null);
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("El cocinero logueado no puede realizar esta operación"); 
			}
		finally{System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@Test
	public void testSaveNegative(){
		try{
			System.out.println("-----------------------------Save Negative-----------------------------");
			authenticate("cook1");
			
			Curriculum result;
			Nutritionist nutritionist;
			
			nutritionist = nutritionistService.findOne(18);
			result = curriculumService.create(nutritionist);
			
			result.setPhoto("https://phototest.com");
			result.setEducationSection("EducationSection Test");
			result.setExperienceSection("ExperienceSection Test");
			result.setHobbiesSection("HobbiesSection Test");
			
			curriculumService.save(result);
			
			System.out.println("Photo: " + result.getPhoto());
			System.out.println("EducationSection: " + result.getEducationSection());
			System.out.println("ExperienceSection: " + result.getExperienceSection());
			System.out.println("HobbiesSection: " + result.getHobbiesSection());
			
			authenticate(null);
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("El cocinero logueado no puede realizar esta operación"); 
			}
		finally{System.out.println("----------------------------------------------------------------"); 
			}
	}
	
	@Test
	public void testDeleteNegative(){
		try{
			System.out.println("-----------------------------Delete Negative-----------------------------");
			authenticate("cook1");
			
			Curriculum result;
			Nutritionist nutritionist;
			
			nutritionist = nutritionistService.findOne(18);
			result = curriculumService.create(nutritionist);
			
			result.setPhoto("https://phototest.com");
			result.setEducationSection("EducationSection Test");
			result.setExperienceSection("ExperienceSection Test");
			result.setHobbiesSection("HobbiesSection Test");
			
			curriculumService.save(result);
			
			System.out.println("Photo: " + result.getPhoto());
			System.out.println("EducationSection: " + result.getEducationSection());
			System.out.println("ExperienceSection: " + result.getExperienceSection());
			System.out.println("HobbiesSection: " + result.getHobbiesSection());
			
			curriculumService.delete(result);
			
			authenticate(null);
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("El cocinero logueado no puede realizar esta operación"); 
			}
		finally{System.out.println("----------------------------------------------------------------"); 
			}
	}

	
	@Test
	public void testfindAllByNutritionistNegative() {
		System.out.println("----------------------------Find All By Nutritionist Negative------------------------------");
		try{
			authenticate("nutritionist1");
			
			Collection<Curriculum> result;
			Nutritionist nutritionist;
			
			nutritionist = nutritionistService.findOne(16);
			
			result = curriculumService.findAllByNutritionist(nutritionist);
			
			for(Curriculum c : result){
				System.out.println("Title: " + c.getPhoto());
				System.out.println("Moment Opening: " + c.getEducationSection());
				System.out.println("Moment Closing: " + c.getExperienceSection());
				System.out.println("Winners: " + c.getHobbiesSection());
			}
			
		}catch (IllegalArgumentException  exception) {
			System.out.println("El cocinero logueado no puede realizar esta operación"); 
			}
		finally{System.out.println("----------------------------------------------------------------"); 
			}
	}
}
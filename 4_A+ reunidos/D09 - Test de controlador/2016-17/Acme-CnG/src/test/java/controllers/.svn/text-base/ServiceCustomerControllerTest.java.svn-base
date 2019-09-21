package controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import controllers.customer.ServiceCustomerController;
import domain.Customer;
import services.ActorService;
import services.CustomerService;
import services.ServiceService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@WebAppConfiguration
public class ServiceCustomerControllerTest {

	
	
	@Mock
	ServiceService mockServiceService;
	@Mock
	View view;
	
	@Mock
	 CustomerService mockCustomerService;
	
	@Mock
	ActorService mockActorService;

	@InjectMocks
	ServiceCustomerController serviceCustomerController;
	MockMvc mockMvc;
	
	
	

	@Before
	public void onSetUp() throws Exception {
	
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(serviceCustomerController).build();
	}

	@Test
	public void testListOffers() throws Exception {
		
	
    Collection<domain.Service> servicios = mockServiceService.findAllOffers();
	
	mockMvc.perform(get("/service/customer/listOffers.do"))
				.andExpect(status().isOk())
				.andExpect(view().name("service/listOffers"))
				.andExpect(model().attribute("requestURI", "service/customer/listOffers.do"))
				.andExpect(model().attribute("services", servicios));
	
	}
	
	
	@Test
	public void testListRequest() throws Exception {
		
	
    Collection<domain.Service> servicios = mockServiceService.findAllRequests();
	
	mockMvc.perform(get("/service/customer/listRequests.do"))
				.andExpect(status().isOk())
				.andExpect(view().name("service/listRequests"))
				.andExpect(model().attribute("requestURI", "service/customer/listRequests.do"))
				.andExpect(model().attribute("services", servicios));
	
	}
	
	
	@Test
	public void testListMyOffer() throws Exception {
		
	User user = new User("customer2","customer2", AuthorityUtils.createAuthorityList("CUSTOMER"));
	TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
    SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);
	
    Customer customer = this.mockCustomerService.findByPrincipal();
    Collection<domain.Service> servicios  = this.mockServiceService.findAllOffersByCustomer(customer);
	
	mockMvc.perform(get("/service/customer/listMyOffers.do").principal(testingAuthenticationToken))
				.andExpect(status().isOk())
				.andExpect(view().name("service/listMyOffers"))
				.andExpect(model().attribute("requestURI", "service/customer/listMyOffers.do"))
				.andExpect(model().attribute("services", servicios));
	
	}
	
	
	@Test
	public void testListMyRequest() throws Exception {
		
	User user = new User("customer2","customer2", AuthorityUtils.createAuthorityList("CUSTOMER"));
	TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
    SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);
	
    Customer customer = this.mockCustomerService.findByPrincipal();
    Collection<domain.Service> servicios  = this.mockServiceService.findAllRequestsByCustomer(customer);
	
	mockMvc.perform(get("/service/customer/listMyRequests.do").principal(testingAuthenticationToken))
				.andExpect(status().isOk())
				.andExpect(view().name("service/listMyRequests"))
				.andExpect(model().attribute("requestURI", "service/customer/listMyRequests.do"))
				.andExpect(model().attribute("services", servicios));
	
	}
	
	
	
	
	@Test
	public void testSearch() throws Exception {
		
	
    String keyword = "tittle";
    Collection<domain.Service> servicios  = this.mockServiceService.getServiceByKeyWord(keyword);
	
	mockMvc.perform(post("/service/customer/search.do").param("keyword", "title"))
				.andExpect(status().isOk())
				.andExpect(view().name("service/customer/search"))
				.andExpect(model().attribute("services", servicios));
	
	}
	

	
	
}
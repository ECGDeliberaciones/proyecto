package test.mapas;

import java.text.ParseException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.LoginService;
import services.CensusService;
import utilities.PopulateDatabase;
import domain.Census;

@ContextConfiguration(
		locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"
		})
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateCensues {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private CensusService censusService;
	
	public void authenticate(String customername){
		UserDetails userDetails;
		TestingAuthenticationToken authenticationToken;
		SecurityContext context;
		
		userDetails = loginService.loadUserByUsername(customername);
		authenticationToken = new TestingAuthenticationToken(userDetails, null);
		context = SecurityContextHolder.getContext();
		context.setAuthentication(authenticationToken);
	}
	
	@Before
	public void setUp(){
		PopulateDatabase.main(null);
	}
	
	/***************************POSITIE
	 * @throws ParseException *******************************/
	
	//Crear una actividad
	@Test
	public void createCensus() throws ParseException{
		
		
		Census c= censusService.create(1, "123456fdas", "10/10/2014", "05/05/2015");
		c.setVersion(1);
		c.setVotacion_id(1);
		
		HashMap<String, Boolean> vpo = c.getVoto_por_usuario();
		
		vpo.put("pepe", true);
		vpo.put("juan", false);
		vpo.put("maria", true);
		
		c.setVoto_por_usuario(vpo);
		
		censusService.save(c);
	}
}



import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.LoginService;

@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class GlobalTest {
	
	@Autowired
	LoginService loginService;
	
	public void authenticate(String username){
		UserDetails userDetails;
		TestingAuthenticationToken authenticationToken;
		SecurityContext context;
		
		userDetails = loginService.loadUserByUsername(username);
		authenticationToken = new  TestingAuthenticationToken(userDetails, null);
		context = SecurityContextHolder.getContext();
		context.setAuthentication(authenticationToken);
	}
	
}

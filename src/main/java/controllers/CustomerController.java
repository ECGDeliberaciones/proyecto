/* CustomerController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.ws.BindingType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.CensusUser;
import domain.Comment;
import domain.Hilo;
import domain.User;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.CommentService;
import services.ThreadService;
import services.UserService;


@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {
	
	@Autowired ThreadService threadService;
	@Autowired UserService userService;
	@Autowired CommentService commentService;
	@Autowired LoginService loginService;
	@Autowired UserDetailsService userDetailsService;
	
	
	
	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("customer/action-1");

		return result;
	}
	
	// Action-2 ---------------------------------------------------------------		

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("customer/action-2");

		return result;
	}
	
	
	
	@RequestMapping("/listThreads")
	public ModelAndView prueba(){
		
		
		ModelAndView result;
		
		Collection<domain.Hilo> threads;
		threads=threadService.findAll();
		
		
		result=new ModelAndView("customer/listThreads");
		result.addObject("threads",threads);
		return result;
		
		
	}
	
	
	@RequestMapping("/seeThread")
	public ModelAndView seeThread(@RequestParam int id){
		
		
	Hilo hilo=threadService.findOne(id);
	ModelAndView result =new ModelAndView("customer/seeThread");
	result.addObject("hilo",hilo);
	result.addObject("comments",hilo.getComments());
	
	Comment comment=new Comment();
	
	comment.setCreationMoment(new Date());
	comment.setThread(hilo);
	comment.setUser(userService.findByPrincipal());
	result.addObject("comment", comment);
	//devuelve hilo mas sus comentarios
	return result;
		
		
		
	}

	@RequestMapping("/saveComment")
	public ModelAndView saveComment(@Valid Comment comment,BindingResult binding){
		
		ModelAndView result=new ModelAndView("redirect:listThreads.do");
		
		if(binding.hasErrors()){
			
			
			result=seeThread(comment.getThread().getId());
			System.out.println(binding.toString());
			
		}else{
			try{
			commentService.save(comment);
			result=seeThread(comment.getThread().getId());
			//debemos comprobar si se guarda o no para guardar tambien el hilo
			}catch(Throwable op){
				result=seeThread(comment.getThread().getId());
				op.printStackTrace();
				
			}
			
		}
		
		return result;
		
		
	}
	
	@RequestMapping("/createThread")
	public ModelAndView createThread(){
		
		
		ModelAndView result=createEditModelAndView(new domain.Hilo());
		
		return result;
		
		
		
		
	}
	
	@RequestMapping("/editThread")
	public ModelAndView editThread(@RequestParam int id){
		Hilo thread=threadService.findOne(id);
		
		ModelAndView result=createEditModelAndView(thread);
		
		return result;
		
	}
	
	@RequestMapping("/saveThread")
	public ModelAndView saveThread(@ModelAttribute("thread") @Valid Hilo thread, BindingResult binding){
		
		
		ModelAndView result=null;
		if(binding.hasErrors()){
			result=createEditModelAndView(thread);
			
			System.out.println(binding.toString());
		}else{
			
			
			try{
				
				
				threadService.save(thread);
				result=new ModelAndView("redirect:listThreads.do");
			}catch(Throwable opps){
				
				result=createEditModelAndView(thread, "Transactional error");
				
			}
			
		}
		
		
		return result;
	}
	
	@RequestMapping("/deleteThread")
	public ModelAndView deleteThread(@RequestParam int id){
		
		
		Hilo thread=threadService.findOne(id);
		
		
		//to do
		
		return new ModelAndView("customer/deleteThread");
		
	}
	
	
	@RequestMapping("/login")
	public ModelAndView login(){
		
		ModelAndView result=new ModelAndView("customer/login");
		
		UserAccount account=new UserAccount();
		Authority au=new Authority();
		au.setAuthority("CUSTOMER");
		account.addAuthority(au);
		result.addObject("account", account);
		
		return result; 
		
		
	}
	
	
	//login from autenticate
	
	
	@RequestMapping("/loginFromAutenticate")
	public ModelAndView loginFromAutentica(){
		
		
		
		//implementar
		
		return null;
		
		
		
		
	}
	
	//login from census, this make a http get to census module and get the json output, after tries to make a login with json output
	//if the person is no present in the bd, save the new person and log in the context.
	//we are to trust the username census give us is unique
	//if the person is present in the bd, log in the context

	
	
	@RequestMapping("/loginFromCensus")
	public ModelAndView loginFromCensus(String username, HttpServletRequest httpRequest) throws JsonParseException, JsonMappingException, IOException{
		
		//implementar
		
		
		System.out.println(username);
		
		//find in the census with json
		
		ObjectMapper objectMapper=new ObjectMapper();
		
	//	Document doc=Jsoup.connect("http://localhost:8080/ADMCensus/census/json_one_user.do?votacion_id=1&username="+username).get();
		//System.out.println(doc.toString());
		
		//si  da error, es que el usuario no esta en el censo
		CensusUser censusUser;
		try{
		 censusUser=objectMapper.readValue(new URL("http://localhost:8080/ADMCensus/census/json_one_user.do?votacion_id=1&username="+username),CensusUser.class);
		
		System.out.println(censusUser.toString());
		Assert.isTrue(censusUser.getUsername()!=null);
		}catch(JsonParseException e){
			
			
			return loginFromCensusFrom();
		}
		
		
		//si no, procedemos
		
		
		ModelAndView result;	
		
		if(userService.findByUsername(censusUser.getUsername())!=null){//esta en la base de datos
			
			
			//nos marcamos el login
			loginMakeFromCensus(userService.findByUsername(username).getUserAccount(), httpRequest);
			
			result=new ModelAndView("customer/listThreads");
			
			
		}else{// no esta, lo registramos
			
			
			User user = new User();
			UserAccount userAccount=new UserAccount();
			Authority a=new Authority();
			a.setAuthority("CUSTOMER");
			userAccount.setUsername(username);
			userAccount.setPassword(new Md5PasswordEncoder().encodePassword(username, null));
			userAccount.addAuthority(a);
			user.setName(username);
			user.setUserAccount(userAccount);
			user.setBanned(false);
			user.setEmail("user@mail");
			user.setLocation("location2");
			user.setNumberOfMessages(0);
			user.setSurname("usernameSurnam");
			user.setComments(new ArrayList<Comment>());
			user.setThreads(new ArrayList<Hilo>());
			
			userService.save(user);
			loginMakeFromCensus(userAccount, httpRequest);
			
			result=new ModelAndView("customer/listThreads");

			
			
		}
		
		
		
		return result;
	}
	
	
	
	
	
	
	@RequestMapping("loginFromCensusForm")
	public ModelAndView loginFromCensusFrom(){
		
		
		
		return new ModelAndView("customer/loginFromCensusForm");
	}
	
	public void loginMakeFromCensus(UserAccount user, HttpServletRequest request){
		
		  try {
	            // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
	            Md5PasswordEncoder md5=new Md5PasswordEncoder();
	        	System.out.println(request.toString());
	        	System.out.println("contraseña pepe de base de datos: "+user.getPassword());
	            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(), null);
	            token.setDetails(new WebAuthenticationDetails(request));
	            DaoAuthenticationProvider authenticator = new DaoAuthenticationProvider();
	            authenticator.setUserDetailsService(userDetailsService);
	           
	            Authentication authentication = authenticator.authenticate(token);
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        } catch (Exception e) {
	            e.printStackTrace();
	            SecurityContextHolder.getContext().setAuthentication(null);
	        }
			
			
		
		
	}
	
	@RequestMapping("/loginMake")
	public ModelAndView loginMake(@Valid UserAccount user, BindingResult bindingResult, HttpServletRequest request){
		
		
		
		ModelAndView result=null;
		
		if(bindingResult.hasErrors()){
			
			
			result=login();
			System.out.println(bindingResult.toString());
			
		}
		
		
		
		if(!(bindingResult.hasErrors()) || bindingResult==null){
			Md5PasswordEncoder md5=new Md5PasswordEncoder();
			//System.out.println("password encodeado de customer: "+md5.encodePassword(user.getPassword(), null));
		//	System.out.println("password de base de datos cust: "+userService.findByPrincipal());
			
			Assert.isTrue(loginService.loadUserByUsername(user.getUsername()).getPassword().equals(md5.encodePassword(user.getPassword(), null)));
			

	        try {
	            // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
	            
	        	System.out.println(request.toString());
	        	
	            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), md5.encodePassword(user.getPassword(), null));
	            token.setDetails(new WebAuthenticationDetails(request));
	            DaoAuthenticationProvider authenticator = new DaoAuthenticationProvider();
	            authenticator.setUserDetailsService(userDetailsService);
	           
	            Authentication authentication = authenticator.authenticate(token);
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        } catch (Exception e) {
	            e.printStackTrace();
	            SecurityContextHolder.getContext().setAuthentication(null);
	        }
			
			
			
			
			result=new ModelAndView("customer/listThreads");
			
			
			
		}
		return result;
		
		
	}
	
	private ModelAndView createEditModelAndView(domain.Hilo thread){
		
		
		return createEditModelAndView(thread, null);
	}
	
	private ModelAndView createEditModelAndView(domain.Hilo thread, String message){
		
		
		ModelAndView result;
		
		
		if(thread.getUser()==null){//NUEVO
			
			thread.setUser(userService.findByPrincipal());
			thread.setCreationMoment(new Date());//necesario para la restricción de fecha de creación
			result=new ModelAndView("customer/editThread");
			result.addObject("user", thread.getUser());
			result.addObject("thread", thread);			
			
		}else{
			
			
			User user=thread.getUser();
			
			result=new ModelAndView("customer/editThread");
			
			result.addObject("thread", thread);
			result.addObject("user", user);
			
			
			
		}
		
		
		return result;
		
		
		
		
		
	}
	

//CREACIÓN LOGIN FROM CABINA DE VOTACIÓN, NOS VIENE UNA ID Y UN TOKEN PARA COMPRAR CON AUTENTIFICACIÓN IMPLEMENTAR ES NECESARIO IMPLEMENTAR - 
	
//CONEXION CON AUTENTICICAION A TRAVES DE JSON PARA PODER LOGUEAR DESDE EL CABINA DE VOTACIÓN
	
	
	
	
	
	//CREACIÓN DE HILOS DESDE CREACIÓN/ADMINISTRACIÓN DE VOTACIONES, LES DEBEMOS DE DAR UN LINK PARA QUE NOS TRAIGA Y CREEMOS UNOS NOSOTROS
	
	
	@RequestMapping("/createThreadFromVotacion")
	public void createTreadFromVotacion(String name){
		
		User user=userService.findByUsername("customer");
		
		Hilo nuevo=new Hilo();
		nuevo.setCreationMoment(new Date());
		nuevo.setText("Hilo sobre la votación: "+name);
		nuevo.setUser(user);
		nuevo.setTitle("Votación "+name);
		nuevo.setComments(new ArrayList<Comment>());
		
		threadService.save(nuevo);
		
		
	}
	
	
	
	
	
	//login desde cabina votacion con toquen, falta implementar por falta de token por parte de autenticación a ellos
}
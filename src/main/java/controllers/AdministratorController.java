/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.hibernate.mapping.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mchange.v2.c3p0.stmt.GooGooStatementCache;



@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	
	
	
	// Constructors -----------------------------------------------------------
	
	public AdministratorController() {
		super();
	}
		
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;
				
		result = new ModelAndView("administrator/action-1");
		
		return result;
	}
	
	// Action-2 ---------------------------------------------------------------
	
	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;
				
		result = new ModelAndView("administrator/action-2");
		
		return result;
	}


	
	//DISPLAY A DASHBOARD WITH NUMER OF CABBAGES SYSTEM STORED AND THE LIST OF CUSTOMERS WHOE HAVE POSTED MORE CABBAGES
	//YO JOSE MARIA CABALLERO ALBA ENTIENDO QUE DEBO DE MOSTRAR EL NUMERO DE ANUNCIOs Y LA LISTA DE CUSTOMER QUE TIENEN ANUNCIOS
	
	
	

	
	
	
	
}
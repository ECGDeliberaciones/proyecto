
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


import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import services.CensusService;
import domain.Census;

@Controller
@RequestMapping("/census")
public class CensusController extends AbstractController {

	
	@Autowired
	private CensusService censusService;
	
	
	// Constructors -----------------------------------------------------------

	public CensusController() {
		super();
	}

	// Create census ---------------------------------------------------------------		

	//Example http://localhost:8080/ADMCensus/census/create.do?idVotacion=100&token_propietario=123456&fecha_inicio=12/11/2014&fecha_fin=12/12/2014
	@RequestMapping(value = "/create", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody/*ModelAndView*/ String create(@RequestParam int idVotacion, String token_propietario, String fecha_inicio, String fecha_fin) throws ParseException {
		//ModelAndView result = new ModelAndView("census/list");
		Census  c = censusService.create(idVotacion, token_propietario, fecha_inicio, fecha_fin);
		try{
			censusService.save(c);
			return new String("[{\"result\":\"yes\"}]");
		}catch(Exception oops){
			return new String("[{\"result\":\"no\"}]");
		}

	}
	
	
	@RequestMapping(value = "/canDelete", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody/*ModelAndView*/ String canDelete(@RequestParam int idVotacion){
		//ModelAndView result = new ModelAndView("census/list");
		
		return censusService.canDelete(idVotacion);

	}
	
	// Devuelve un censo en formato JSON ---------------------------------------------------------------	
	
	 @RequestMapping(value = "/json_one_user", method = RequestMethod.GET, produces="application/json")  
	 public @ResponseBody  String  getCensus(@RequestParam int votacion_id, String username) {  
		 return censusService.createResponseJson(votacion_id, username);
	 }  
	 
	// Devuelve todos los censos en formato JSON ---------------------------------------------------------------	
	 
	 @RequestMapping(value ="/json_all", method = RequestMethod.GET, produces="application/json")    
	 public @ResponseBody  Collection<Census> getAllCensus() {  
		 Collection<Census> cs;
		 cs = censusService.findAll();
		 return cs;
	 }  
	 
	// Devuelve todos los censos en formato JSON de unusuario ---------------------------------------------------------------	
	 
	 @RequestMapping(value ="/json_By_User", method = RequestMethod.GET, produces="application/json")    
	 public ModelAndView getAllCensusByUser(@RequestParam String username) {  
		 ModelAndView result = null; 
		 Collection<Census> cs;
		 cs = censusService.findCensusActiveByUser(username);
		 
		// result = new ModelAndView("redirect:/census/details.do?censusId="+censusId);
		 return null;
		 
	 }  
	 
	 @RequestMapping(value ="/prueba", method = RequestMethod.GET, produces="application/json")    
	 public @ResponseBody  String prueba() {  
		 String res = "";
		 res += "[{\"username\":\"name1\",\"password\": \"pass1\", \"email\":\"email1\"},]";
		 /*res += "{\"username\":name2,\"password\": \"pass2\", \"email\":\"email2\"},";
		 res += "{\"username\":\"name3\",\"password\": \"pass3\", \"email\":\"email3\"},";
		 res += "{\"username\":\"name4\",\"password\": \"pass4\", \"email\":\"email4\"},";
		 res += "{\"username\":\"name5\",\"password\": \"pass5\", \"email\":\"email5\"},";
		 res += "{\"username\":\"name6\",\"password\": \"pass6\", \"email\":\"email6\"},";
		 res += "{\"username\":\"name7\",\"password\": \"pass7\", \"email\":\"email7\"},";
		 res += "{\"username\":\"name8\",\"password\": \"pass8\", \"email\":\"email8\"}]";*/

		 return res;
	 }  
	 
	 
	 
	 
	 
	// Add Users ----------------------------------------------------------------
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUser(@RequestParam int censusId, String userneme, String token) {
		ModelAndView result = null;
		try{
			
			censusService.addUserToCensu(censusId,userneme ,token);
			result = new ModelAndView("redirect:/census/edit.do?censusId="+censusId);
			
		}catch(Exception oops){
			result = new ModelAndView("redirect:/census/edit.do?censusId="+censusId);
			result.addObject("message", "No se pudo añadir el usuario");
			oops.getStackTrace();
		}
		

		return result;
	}

	// Remove Users ----------------------------------------------------------------
	@RequestMapping(value = "/removeUser", method = RequestMethod.GET)
	public ModelAndView removeUser(@RequestParam int censusId, String username, String token) {
		ModelAndView result = null;
		try{
			
			 censusService.removeUserToCensu(censusId, username, token);
			
			result = new ModelAndView("redirect:/census/details.do?censusId="+censusId);
			
		}catch(Exception oops){
			result = new ModelAndView("redirect:/census/details.do?censusId="+censusId);
			result.addObject("message", "No se pudo eliminar el usuario");
			oops.getStackTrace();
		}
		

		return result;
	}
	
	
	// Update Users ----------------------------------------------------------------
	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public @ResponseBody/*ModelAndView*/ String updateUser(@RequestParam int censusId, String token) {
		//ModelAndView result = null;
		try{
			censusService.updateUser(censusId, token);
			//String requestURI = "census/list.do";
			//result.addObject("censuses", censusService.findAll());
			//result.addObject("requestURI", requestURI);
			return new String("{\"result\":\"user update sucefull\"}");
		}catch(Exception oops){
			return new String("{\"result\":\"update user error\"}");
		}
		

		//return result;
	}
	
	// Lista los censos del sistema  ---------------------------------------------------------------	

	@RequestMapping("/list")
	public ModelAndView list(){
		String requestURI = "census/list.do";
		ModelAndView result = new ModelAndView("census/list");
		result.addObject("censuses", censusService.findAll());
		result.addObject("requestURI", requestURI);
		return result;
	}
	
	
	// Details ----------------------------------------------------------------
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView details(@RequestParam int censusId) {
		ModelAndView result;
		Census census= censusService.findOne(censusId);
		result=createEditModelAndView(census);

		return result;
	}
	/*
	// Editar un censo para añadir o quitar usuarios ---------------------------------------------------------------	

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int censusId) {
		ModelAndView result = new ModelAndView("census/manage");
		Census census= censusService.findOne(censusId);
		Collection <User> users = userService.findAll();
		result.addObject("users", users);
		result.addObject("census", census);
		

		return result;
	}
	*/
	// Delete ----------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int censusId, String token) {
		ModelAndView result = null;
		try{
			censusService.delete(censusId, token);
			result = new ModelAndView("redirect:/census/list.do");
			
		}catch(Exception oops){
			result = new ModelAndView("redirect:/census/details.do?censusId="+censusId);
			result.addObject("message", "No se pudo borrar el censo");
			oops.getStackTrace();
		}
		

		return result;
	}
		

	//Save
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Census census, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(census);
		} else {
			try {
				censusService.save(census);
				result = new ModelAndView("redirect:/census/list.do");
				
			}catch (Throwable oops) {
				result = createEditModelAndView(census, "census.commit.error");				
			}
		}
		
		return result;
	}
	
	// Ancillary methods ------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Census census) {
		ModelAndView result;

		result = createEditModelAndView(census, null);
		
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(Census census, String message) {
		ModelAndView result = new ModelAndView("census/create");
		
		if(census.getId() != 0){
			result = new ModelAndView("census/details");
			//result.addObject("users", userService.findAllByCensus(census.getId()));
			//result.addObject("users", userRepository.findAll());
		}
		HashMap<String, Boolean> mapa = census.getVoto_por_usuario();
		result.addObject("census", census);
		result.addObject("mapa",mapa );
		result.addObject("message", message); //esta definida en el layout, no en la vista edit
		
		return result;

	}
	
}	
	
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CensusRepository;
import domain.Census;


@Service
@Transactional
public class CensusService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private CensusRepository censusRepository;
	
	// Supporting services ----------------------------------------------------

	
	// Constructors -----------------------------------------------------------
	
	public CensusService(){
		super();
	}	
	
	// Others Methods
	
	/****
	 *Método que crea un censo creando el mapa y recibiendo el token del creador
	 *y la id de la votación
	 *
	 *	
	 * @param int idVotacion, String token_propietario
	 * @return census
	 * @throws ParseException 
	 * 
	 ****/
	public Census create(int idVotacion, String token_propietario, String fecha_inicio, String fecha_fin) throws ParseException{ 
		Census c = new Census();
		
		
		// Usaremos el formato de fecha que necesitemos
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = deadLine;
		Date fecha = sdf.parse(dateInString);
		c.setDeadLine(fecha);*/
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fecha_comienzo = format.parse(fecha_fin);
		Date fecha_final = format2.parse(fecha_fin);
		
		c.setFecha_fin(fecha_final);
		c.setFecha_inicio(fecha_comienzo);
		c.setToken_propietario(token_propietario);
		c.setVotacion_id(idVotacion);
		HashMap<String, Boolean> vpo = new HashMap<String, Boolean>();
		c.setVoto_por_usuario(vpo);
				
		return c;
	}
	
	/****
	 * 
	 * Persiste un censo 
	 * Tambien actualizara este para añadir usuarios y cambiar su valores de voto
	 * 
	 * @param census
	 * @return census
	 */
	public Census save(Census census){
		Census c = censusRepository.save(census);
		return c;
	}
	
	
	//Other Methods
	
	/****
	 * 
	 * Borra un censo determinado cumpliendo la condicion que el mapa de votos por usuarios debe estar vacia
	 * 
	 * @param censusId
	 * @param token
	 */
	public void delete(int censusId, String token){
		Census c = findOne(censusId);
		Assert.isTrue(c.getVoto_por_usuario().isEmpty());//Puedo borrarlo siempre y cuando no haya añadido usuario
		Assert.isTrue(c.getToken_propietario().equals(token));
		censusRepository.delete(censusId);
	}
	
	
	
	/******
	 * 
	 * Encuentra un censo determidado por su id
	 * 
	 * @param censusId
	 * @return census
	 */
	public Census findOne(int censusId){
		Census c = censusRepository.findOne(censusId);
		Assert.notNull(c);
		return c;
	}
	
	
	/*****
	 * 
	 * Encuentra un censo por la votacion creada
	 * 
	 * @param idVotacion
	 * @return census
	 */
	public Census findOneByVote(int idVotacion){
		Census c = censusRepository.findCensusByVote(idVotacion);
		Assert.notNull(c);
		return c;
	}
	
	/******
	 * 
	 * Metodo que devuelve un json informando sbre un determinado usuario y su estado en el boto
	 * 
	 * @param idVotacion
	 * @param token
	 * @return String
	 */
	public String createResponseJson(int idVotacion, String username){
		String response = "";
		Census c = findOneByVote(idVotacion);
		//formato: idVotacion, token usuario, true/false
		if(c.getVoto_por_usuario().get(username)){
			response = response +  "{\"idVotacion\":" + idVotacion + ",\"username\":\"" + username + "\",\"result\":" + c.getVoto_por_usuario().get(username) + "}";
		} else {
			response = response +  "{\"result\":" + "user dont exist}";

		}
		return  response;
	}
	
	
	/*****
	 * 
	 * Encuentra todos los censos del sistema
	 * 
	 * @return Collection<Census>
	 */
	public Collection<Census> findAll(){
		return censusRepository.findAll();
	}

	
	
	/****
	 * 
	 * Añade un usuario con un token determidado a un censo
	 * @param censusId
	 * @param username
	 * @param token
	 */
	public void addUserToCensu(int censusId, String username, String token) {
		Census c = findOne(censusId);
		Assert.isTrue(c.getToken_propietario().equals(token));
		HashMap<String, Boolean> vpo = c.getVoto_por_usuario();
		
		if (!vpo.containsKey(username)){
			vpo.put(username, false);
		}

		c.setVoto_por_usuario(vpo);
		save(c);
		
		
	}
	
	
	
	/****
	 * 
	 * Elimina un usuario con un token determidado a un censo,
	 * cumpliendo la condicion de que el usuario no tenga voto en ese censo
	 * @param censusId
	 * @param username
	 * @param token
	 */
	public void removeUserToCensu(int censusId, String username, String token) {
		Census c = findOne(censusId);
		Assert.isTrue(c.getToken_propietario().equals(token));
		HashMap<String, Boolean> vpo = c.getVoto_por_usuario();
		
		if (vpo.containsKey(username) && !vpo.get(username)){
			vpo.remove(username);
		}

		c.setVoto_por_usuario(vpo);
		save(c);
		
		
	}

	
	/***
	 * Metodo utilizado por cabina para actualizar el estado de voto de un usuario
	 * 
	 * @param censusId
	 * @param token
	 */
	public void updateUser(int censusId, String token) {
		Census c = findOne(censusId);
		HashMap<String, Boolean> vpo = c.getVoto_por_usuario();
		
		if (vpo.containsKey(token) && !vpo.get(token)){
			
			vpo.remove(token);
			vpo.put(token, true);
		}

		c.setVoto_por_usuario(vpo);
		save(c);
		
		
	}
	
	
	/****
	 * Metodo que devuelve todas las votaciones que un usuario no a votado y esta activa
	 * @param username
	 * @return Collection<census>
	 *
	 *
	 */
	public Collection<Census> findCensusActiveByUser(String username){
		Collection<Census> cs = new ArrayList<Census>();
		Collection<Census> aux = findAll(); //Obtengo todos los censos
		
		for(Census c: aux){
			//Si el usuario esta en el censo, la votación esta activa y no ha votado tengo que mostrar su censo.
			if(c.getVoto_por_usuario().containsKey(username) && !c.getVoto_por_usuario().get(username) && votacionActiva(c.getFecha_inicio(),c.getFecha_fin(), c)){
				cs.add(c);
			}
		}
		
		return cs;
	}
	
	
	
	/****
	 * Metodo para buscar un censo pur su votacion
	 * @param idVotacion
	 * @return census
	 */
	public Census findCensusByVote(int idVotacion){
		return censusRepository.findCensusByVote(idVotacion);
	}
	
	/****
	 * Metodo para devolver un json para saber si puede borrar o no puede borrar una votacion
	 * 
	 * 
	 * @param idVotacion
	 * @return string format json
	 */
	public String canDelete(int idVotacion){
		String res = "";
		
		Census c = findCensusByVote(idVotacion);
		
		if(c.getVoto_por_usuario().isEmpty()){
			res = "[{\"result\":\"yes\"}]";
			censusRepository.delete(c);
		}else{
			res = "[{\"result\":\"no\"}]";
		}
		
		return res;
	}
	
	
	private boolean votacionActiva(Date fecha_inicio,Date fecha_fin, Census c){
		Boolean res = false;
		Date fecha_actual = new Date();
		if(fecha_actual.before(fecha_inicio) && !fecha_fin.before(fecha_actual)){
			res = true;
		}
		
		return res;
		
	}
}



package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ActorRepository;
import domain.Actor;
@Service
@Transactional
public class ActorService {
@Autowired
	private ActorRepository actorRepository;
public Collection<Actor>  findAll(){
return actorRepository.findAll();
}
public Actor findOne(Integer valueOf) {
return actorRepository.findOne(valueOf);
}
public Actor save(Actor actor){
return actorRepository.save(actor);
}
}
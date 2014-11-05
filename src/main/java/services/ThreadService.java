
package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ThreadRepository;
import domain.Hilo;
@Service
@Transactional
public class ThreadService {
@Autowired
	private ThreadRepository threadRepository;
public Collection<Hilo>  findAll(){
return threadRepository.findAll();
}
public Hilo findOne(Integer valueOf) {
return threadRepository.findOne(valueOf);
}
public Hilo save(Hilo thread){
return threadRepository.save(thread);
}
}

package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.AdministratorRepository;
import domain.Administrator;
@Service
@Transactional
public class AdministratorService {
@Autowired
	private AdministratorRepository administratorRepository;
public Collection<Administrator>  findAll(){
return administratorRepository.findAll();
}
public Administrator findOne(Integer valueOf) {
return administratorRepository.findOne(valueOf);
}
public Administrator save(Administrator administrator){
return administratorRepository.save(administrator);
}
}
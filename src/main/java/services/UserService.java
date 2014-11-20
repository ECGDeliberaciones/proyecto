
package services;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserRepository;
import security.LoginService;
import security.UserAccountRepository;
import domain.User;
@Service
@Transactional
public class UserService {
@Autowired
	private UserRepository userRepository;
@Autowired
	private LoginService loginService;
public Collection<User>  findAll(){
return userRepository.findAll();
}
public User findOne(Integer valueOf) {
return userRepository.findOne(valueOf);
}
public User save(User user){
return userRepository.save(user);
}
public User findByPrincipal() {
	// TODO Auto-generated method stub
	return userRepository.findOneByPrincipal(loginService.getPrincipal().getId());
}
public User findByUsername(String username) {
	// TODO Auto-generated method stub
	return userRepository.findByUsername(username);
}
}
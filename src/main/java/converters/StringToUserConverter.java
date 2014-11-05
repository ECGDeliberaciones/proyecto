

package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.UserService;
import domain.User;
@Component
@Transactional
public class StringToUserConverter implements Converter<String,User>{
@Autowired
private UserService userService;
@Override
public User convert(String arg0) {
return userService.findOne(Integer.valueOf(arg0));
}
}

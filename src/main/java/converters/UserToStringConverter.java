
package converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.User;
@Component
@Transactional
public class UserToStringConverter implements Converter<User, String> {
@Override
public String convert(User arg0) {
return String.valueOf(arg0.getId());
}
}

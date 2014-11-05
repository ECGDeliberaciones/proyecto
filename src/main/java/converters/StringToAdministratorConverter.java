

package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.AdministratorService;
import domain.Administrator;
@Component
@Transactional
public class StringToAdministratorConverter implements Converter<String,Administrator>{
@Autowired
private AdministratorService administratorService;
@Override
public Administrator convert(String arg0) {
return administratorService.findOne(Integer.valueOf(arg0));
}
}



package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.ThreadService;
import domain.Hilo;
@Component
@Transactional
public class StringToThreadConverter implements Converter<String,Hilo>{
@Autowired
private ThreadService threadService;
@Override
public Hilo convert(String arg0) {
return threadService.findOne(Integer.valueOf(arg0));
}
}

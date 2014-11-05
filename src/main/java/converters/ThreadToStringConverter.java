
package converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Hilo;
@Component
@Transactional
public class ThreadToStringConverter implements Converter<Hilo, String> {
@Override
public String convert(Hilo arg0) {
return String.valueOf(arg0.getId());
}
}

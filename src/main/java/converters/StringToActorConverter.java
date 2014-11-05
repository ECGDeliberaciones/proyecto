

package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.ActorService;
import domain.Actor;
@Component
@Transactional
public class StringToActorConverter implements Converter<String,Actor>{
@Autowired
private ActorService actorService;
@Override
public Actor convert(String arg0) {
return actorService.findOne(Integer.valueOf(arg0));
}
}

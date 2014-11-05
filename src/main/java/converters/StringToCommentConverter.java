

package converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import services.CommentService;
import domain.Comment;
@Component
@Transactional
public class StringToCommentConverter implements Converter<String,Comment>{
@Autowired
private CommentService commentService;
@Override
public Comment convert(String arg0) {
return commentService.findOne(Integer.valueOf(arg0));
}
}

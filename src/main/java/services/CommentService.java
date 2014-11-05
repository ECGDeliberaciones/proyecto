
package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.CommentRepository;
import domain.Comment;
@Service
@Transactional
public class CommentService {
@Autowired
	private CommentRepository commentRepository;
public Collection<Comment>  findAll(){
return commentRepository.findAll();
}
public Comment findOne(Integer valueOf) {
return commentRepository.findOne(valueOf);
}
public Comment save(Comment comment){
return commentRepository.save(comment);
}
}
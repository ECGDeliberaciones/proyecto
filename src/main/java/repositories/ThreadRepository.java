package repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.Hilo;
@Repository
public interface ThreadRepository extends JpaRepository<Hilo, Integer> {
}

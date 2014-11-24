package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Census;


@Repository
public interface CensusRepository extends JpaRepository<Census, Integer>{
	
	@Query("select c from Census c where c.votacion_id = ?1")
	public Census findCensusByVote(int idVotacion);
	

}

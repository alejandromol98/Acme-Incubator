
package acme.features.entrepreneur.workProgramme;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workProgrammes.WorkProgramme;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurWorkProgrammeRepository extends AbstractRepository {

	@Query("select w from WorkProgramme w where w.id = ?1")
	WorkProgramme findOneById(int id);

	@Query("select w from WorkProgramme w where w.investmentRound.id = ?1")
	Collection<WorkProgramme> findManyAll(int investmentRoundId);

}

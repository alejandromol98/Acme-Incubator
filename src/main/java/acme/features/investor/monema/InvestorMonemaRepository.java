
package acme.features.investor.monema;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.monemas.Monema;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorMonemaRepository extends AbstractRepository {

	@Query("select m from Monema m where m.id = ?1")
	Monema findOneById(int id);

	@Query("select m from Monema m where m.investmentRound.id = ?1")
	Collection<Monema> findManyAll(int investmentRoundId);

}

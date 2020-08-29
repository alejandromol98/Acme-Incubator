
package acme.features.bookkeeper.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select i from InvestmentRound i where i.isFinalMode = true")
	Collection<InvestmentRound> findManyAll();

	@Query("select distinct a.investmentRound from AccountingRecord a where a.bookkeeper.id = ?1")
	Collection<InvestmentRound> findManyByBookkeeper(int bookkeeperId);

	@Query("select distinct i from InvestmentRound i inner join Application a on a.investmentRound.id = i.id where a.status = 1")
	Collection<InvestmentRound> findInactiveInvestmentRounds();

}

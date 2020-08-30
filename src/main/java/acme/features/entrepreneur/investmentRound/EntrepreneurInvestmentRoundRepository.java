
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.applications.Application;
import acme.entities.discussionForums.DiscussionForum;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.messages.Message;
import acme.entities.workProgrammes.WorkProgramme;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select i from InvestmentRound i where i.entrepreneur.id = ?1")
	Collection<InvestmentRound> findManyAll(int entrepreneurId);

	@Query("select sum(w.budget.amount) from WorkProgramme w where w.investmentRound.id = ?1")
	Double getSumWorkProgrammesByInvRound(int id);

	@Query("select count(w.id) from WorkProgramme w where w.investmentRound.id = ?1")
	Integer getCountWorkProgrammesByInvRound(int id);

	@Query("select w from WorkProgramme w where w.investmentRound.id = ?1")
	Collection<WorkProgramme> getWorkProgrammesByInvRound(int id);

	@Query("select a from Application a where a.investmentRound.id = ?1")
	Collection<Application> getApplicationsByInvRound(int id);

	@Query("select count(a.id) from Application a where a.investmentRound.id = ?1")
	Integer getCountAppsByInvRound(int id);

	@Query("select a from AccountingRecord a where a.investmentRound.id = ?1")
	Collection<AccountingRecord> getAccountingRecordsByInvRound(int id);

	@Query("select d from DiscussionForum d where d.investmentRound.id = ?1")
	Collection<DiscussionForum> getDiscussionForumsByInvRound(int id);

	@Query("select m from Message m where m.forum.investmentRound.id = ?1")
	Collection<Message> getMessagesByInvRound(int id);

}

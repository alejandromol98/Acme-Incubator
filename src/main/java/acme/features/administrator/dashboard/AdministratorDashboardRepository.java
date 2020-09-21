
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select 1.0 * count(m.id) / (select count(i) from InvestmentRound i) from Monema m")
	Double findInvRoundWithMonema();

	@Query("select 1.0 * count(a.id) / (select count(b) from Application b) from Application a where a.link != '' ")
	Double findApplicationsWithLink();

	@Query("select 1.0 * count(a.id) / (select count(b) from Application b) from Application a where a.password != '' ")
	Double findApplicationsWithPassword();

}

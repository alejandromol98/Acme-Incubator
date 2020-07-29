
package acme.features.anonymous.molinaBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.molinaBulletins.MolinaBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousMolinaBulletinRepository extends AbstractRepository {

	@Query("select m from MolinaBulletin m")
	Collection<MolinaBulletin> findMany();

}

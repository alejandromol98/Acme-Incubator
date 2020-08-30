
package acme.features.authenticated.discussionForum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.discussionForums.DiscussionForum;
import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedDiscussionForumRepository extends AbstractRepository {

	@Query("select d from DiscussionForum d where d.id = ?1")
	DiscussionForum findOneById(int id);

	@Query("select d from DiscussionForum d")
	Collection<DiscussionForum> findManyAll();

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedByUserAccountId(int id);

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findMessagesByDiscussionForumId(int discForId);

}

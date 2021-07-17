package backend.nyc.com.titan.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SessionRepository extends JpaRepository<SessionDB, SessionRelationshipId> {
    List<SessionDB> getSessionsById(String id);
    List<SessionDB> findDistinctSessionsById(String id);

    @Query(value = "select board from session_fct where board_version in (select MAX(board_version) from session_fct where session_id = ?1)", nativeQuery = true)
    String getBoardFromLatestVersionOfSession(String sessionId);

    @Query(value = "select * from session_fct where board_version in (select MAX(board_version) from session_fct where session_id = ?1)", nativeQuery = true)
    SessionDB getLatestVersionOfSession(String sessionId);
}

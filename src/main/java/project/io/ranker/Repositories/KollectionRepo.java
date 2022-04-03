package project.io.ranker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.io.ranker.models.KollectionModel;

@Repository
public interface KollectionRepo extends JpaRepository<KollectionModel, Long> {

}

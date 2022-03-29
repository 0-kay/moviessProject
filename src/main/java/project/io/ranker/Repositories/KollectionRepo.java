package project.io.ranker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.io.ranker.models.KollectionModel;

public interface KollectionRepo extends JpaRepository<Long, KollectionModel> {

}

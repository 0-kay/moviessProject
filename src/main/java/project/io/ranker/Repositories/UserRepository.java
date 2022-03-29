package project.io.ranker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.io.ranker.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

}

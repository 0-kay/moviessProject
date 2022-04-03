package project.io.ranker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.io.ranker.models.UserDetailsImpl;
import project.io.ranker.models.UserModel;

import java.util.Optional;

@Repository
public interface UserModelRepo extends JpaRepository<UserDetailsImpl,Long> {
    Optional<UserModel> findByUsername(String username);
    Boolean existsByUsername(String username);
}

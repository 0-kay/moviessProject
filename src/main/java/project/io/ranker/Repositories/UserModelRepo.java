package project.io.ranker.Repositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.io.ranker.models.UserDetailsImpl;
import project.io.ranker.models.UserModel;

import java.util.Optional;


public interface UserModelRepo extends JpaRepository<UserModel,Long> {
    Optional<UserModel> findByUsername(String username);
    Boolean existsByUsername(String username);
}

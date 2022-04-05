package project.io.ranker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.io.ranker.models.EnumRole;
import project.io.ranker.models.RoleModel;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByName(EnumRole name);
    Boolean existsByName(EnumRole name);
}

package project.io.ranker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.io.ranker.models.ItemModel;

@Repository
public interface ItemRepositories extends JpaRepository<ItemModel,Long> {

}

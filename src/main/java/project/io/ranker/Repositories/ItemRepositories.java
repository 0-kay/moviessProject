package project.io.ranker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.io.ranker.models.ItemModel;

public interface ItemRepositories extends JpaRepository<ItemModel,Long> {

}

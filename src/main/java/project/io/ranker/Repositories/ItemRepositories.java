package project.io.ranker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.io.ranker.models.ItemModel;
import project.io.ranker.models.KollectionModel;

import java.util.List;

@Repository
public interface ItemRepositories extends JpaRepository<ItemModel,Long> {
    List<ItemModel> findByKollectionItems(KollectionModel kollectionItems);
}

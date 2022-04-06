package project.io.ranker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.io.ranker.models.ItemModel;
import project.io.ranker.models.KollectionModel;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepositories extends JpaRepository<ItemModel,Long> {
    List<ItemModel> findByKollectionItems(KollectionModel kollectionItems);

    Collection<Object> findByKollectionItems(Optional<KollectionModel> byId);
}

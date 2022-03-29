package project.io.ranker.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KollectionModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    private String name;

    @OneToMany(mappedBy = "kollectionItems")
    private Set<ItemModel> kollectionItemsItems;

}

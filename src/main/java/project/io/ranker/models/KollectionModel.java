package project.io.ranker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class KollectionModel {

    @Id
    private Long Id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "item_model_id")
    private ItemModel itemModel;

}

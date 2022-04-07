package project.io.ranker.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemModel {


   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    private String name;
    private String imgUrl;
    private Integer Points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kollections_items_id")
    private KollectionModel kollectionItems;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemModel)) return false;
        ItemModel itemModel = (ItemModel) o;
        return Objects.equals(getId(), itemModel.getId()) && getName().equals(itemModel.getName()) && getImgUrl().equals(itemModel.getImgUrl()) && Objects.equals(getPoints(), itemModel.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getImgUrl(), getPoints());
    }
}

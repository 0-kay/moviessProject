package project.io.ranker.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

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

    public ItemModel(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

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

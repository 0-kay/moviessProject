package project.io.ranker.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ItemDTO {
    private Long Id;
    private String name;
    private String imgUrl;
    private Integer points;

    public ItemDTO(Long id, String name, String imgUrl, Integer points) {
        Id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.points=points;
    }

    public ItemDTO() {
    }

    public ItemDTO(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.points=points;
    }
}

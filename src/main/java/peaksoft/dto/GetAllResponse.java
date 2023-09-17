package peaksoft.dto;


import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Category;
import peaksoft.models.Brand;
import peaksoft.models.Comment;
import peaksoft.models.Favorite;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter


public class GetAllResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private List<String> images;
    private String characteristic;
    private boolean isFavorite;
    private String madeIn;
    private Category category;
    private Brand brand;
    private List<Comment> comments;
    private List<Favorite>favorites;

    public GetAllResponse(Long id, String name, BigDecimal price, String characteristic, String madeIn, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.madeIn = madeIn;
        this.category = category;
    }
}

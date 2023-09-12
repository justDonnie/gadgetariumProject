package peaksoft.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Category;
import peaksoft.models.Basket;
import peaksoft.models.Brand;
import peaksoft.models.Comment;
import peaksoft.models.Favorite;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
public class ProductResponse{
    private Long id;
    private String name;
    private BigDecimal price;
    private List<String> images;
    private String characteristic;
    private String madeIn;
    private Category category;
    private List<String> comment;
    private int countFavorite;

    public ProductResponse(Long id, String name, BigDecimal price, String characteristic, String madeIn, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.madeIn = madeIn;
        this.category = category;
    }
}

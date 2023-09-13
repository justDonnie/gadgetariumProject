package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Category;
import peaksoft.models.Brand;

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
    private Brand brand;
    private Category category;
    private List<String> comment;
    private int countFavorite;

    public ProductResponse(Long id, String name, BigDecimal price, String characteristic, String madeIn,Brand brand, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.brand=brand;
        this.madeIn = madeIn;
        this.category = category;
    }
}

package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Category;
import peaksoft.models.Brand;
import peaksoft.models.Comment;

import java.math.BigDecimal;
import java.util.List;


public class ProductResponse{
    private Long id;
    private String name;
    private BigDecimal price;
    private List<String> images;
    private String characteristic;
    private boolean isFavorite;
    private String madeIn;
    private Category category;
    private Brand brand;
    private String comment;

    public ProductResponse(Long id, String name, BigDecimal price,String characteristic, boolean isFavorite, String madeIn, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.category = category;
    }

    public ProductResponse(Long id, String name, BigDecimal price, String characteristic, boolean isFavorite, String madeIn, Category category, Brand brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.category = category;
        this.brand = brand;
    }

    public ProductResponse(Long id, String name, BigDecimal price, String characteristic, boolean isFavorite, String madeIn, Category category, Brand brand, String comment) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.category = category;
        this.brand = brand;
        this.comment = comment;
    }

    public ProductResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

package peaksoft.dto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Category;
import peaksoft.models.Basket;
import peaksoft.models.Brand;

import java.math.BigDecimal;
import java.util.List;

@Builder

public record ProductRequest(
       String name,
       BigDecimal price,
       List<String>images,
       String characteristic,
       String madeIn,
       Brand brand,
       Category category
) {
    public ProductRequest {
    }
}

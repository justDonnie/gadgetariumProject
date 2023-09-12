package peaksoft.dto;

import lombok.Builder;
import peaksoft.models.Product;

import java.util.List;
@Builder
public record BrandResponse(
        Long id,
        String brandName,
        String image,
        List<Product> products) {
    public BrandResponse {
    }
}

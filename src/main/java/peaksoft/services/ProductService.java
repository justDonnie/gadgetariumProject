package peaksoft.services;

import peaksoft.dto.ProductRequest;
import peaksoft.dto.ProductResponse;
import peaksoft.dto.SimpleResponse;

import java.util.List;

public interface ProductService {

    SimpleResponse saveProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse findProductById(Long prodId);

    SimpleResponse updateProduct(Long productId, ProductRequest newProductRequest);

    SimpleResponse deleteProduct(Long productId);

    ProductResponse getProductWithComment(Long productId);
}

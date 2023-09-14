package peaksoft.services;

import peaksoft.dto.*;
import peaksoft.enums.Category;

import java.util.List;

public interface ProductService {

    SimpleResponse saveProduct(ProductRequest productRequest,Long brandId);

    List<ProductResponse> getAllProducts();

    List<GetAllResponse> findAllProducts();

    ProductResponse findProductById(Long prodId);

    SimpleResponse updateProduct(Long productId, ProductRequest newProductRequest);

    SimpleResponse deleteProduct(Long productId);

    List<ProductResponse> getProductByCategoryAndPrice(String ascOrDesc,Category category);

    ProductWithCommentsResponse getProductWithComment(Long productId);
}

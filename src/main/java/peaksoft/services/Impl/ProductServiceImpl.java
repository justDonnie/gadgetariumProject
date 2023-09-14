package peaksoft.services.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.*;
import peaksoft.enums.Category;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Brand;
import peaksoft.models.Comment;
import peaksoft.models.Product;
import peaksoft.repositories.BrandRepository;
import peaksoft.repositories.CommentRepository;
import peaksoft.repositories.ProductRepository;
import peaksoft.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;
    private final BrandRepository brandRepository;

    @Override
    public SimpleResponse saveProduct(ProductRequest productRequest,Long brandId) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() ->
                new NotFoundException("Brand with ID: " + brandId + " is not found !!!"));
        Product product = new Product();
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setImages(productRequest.images());
        product.setCharacteristic(productRequest.characteristic());
        product.setFavorite(productRequest.isFavorite());
        product.setMadeIn(productRequest.madeIn());
        product.setBrand(brand);
        product.setCategory(productRequest.category());
        productRepository.save(product);
        log.info("Product is successfully saved");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product is successfully saved !!!")
                .build();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public List<GetAllResponse> findAllProducts() {
        List<GetAllResponse>allResponses = productRepository.findAllProduct();
        for (GetAllResponse p:allResponses) {
            if (p.getImages()==null){

            }
        }

        return productRepository.findAllProduct();
    }


    @Override
    public ProductResponse findProductById(Long prodId) {
        return productRepository.findProductById(prodId)
                .orElseThrow(() -> {
                    String massage = "Product with ID : " + prodId + " is not found!";
                    log.error(massage);
                    return new NotFoundException(massage);
                });
    }

    @Override
    public SimpleResponse updateProduct(Long productId, ProductRequest newProductRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> {
            String massage = "Product with ID : " + productId + " is not found!";
            log.error(massage);
            return new NotFoundException(massage);
        });
        product.setName(newProductRequest.name());
        product.setPrice(newProductRequest.price());
        product.setImages(newProductRequest.images());
        product.setCharacteristic(newProductRequest.characteristic());
        product.setMadeIn(newProductRequest.madeIn());
        product.setCategory(newProductRequest.category());
        productRepository.save(product);
        log.info("Product is successfully updated!!!");
        return new SimpleResponse(
                HttpStatus.OK,
                "Product with ID : " + productId + " is successfully updated !!!"
        );
    }

    @Override
    public SimpleResponse deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product with ID: " + productId + " is not found!!!");
        }
        productRepository.deleteById(productId);
        log.info("Product is successfully deleted!!!");
        return new SimpleResponse(
                HttpStatus.OK,
                "Product with ID: " + productId + " is successfully deleted !!!"
        );
    }

    @Override
    public List<ProductResponse> getProductByCategoryAndPrice(String ascOrDesc, Category category) {
        if (ascOrDesc.equalsIgnoreCase("asc")||(ascOrDesc.equalsIgnoreCase("desc"))){
            if (ascOrDesc.equalsIgnoreCase("asc")){
                return productRepository.getProductByCategoryAndPriceAsc(category);
            } else if (ascOrDesc.equalsIgnoreCase("desc")) {
                return productRepository.getProductByCategoryAndPriceDesc(category);
            }
        }else {
            throw new NotFoundException("Input the correct command !!!");
        }
        return null;
    }


    @Override
    public ProductWithCommentsResponse getProductWithComment(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new NotFoundException("Product with id: " + productId + " is not found"));

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setCharacteristic(product.getCharacteristic());
        productResponse.setFavorite(product.isFavorite());
        productResponse.setMadeIn(product.getMadeIn());
        productResponse.setBrand(product.getBrand());
        productResponse.setCategory(product.getCategory());
        List<Comment> comments = commentRepository.findAllByProductId(productId);
        ProductWithCommentsResponse response = new ProductWithCommentsResponse(productResponse, comments);
        return ProductWithCommentsResponse.builder()
                .comments(response.getComments())
                .productResponse(response.getProductResponse())
                .build();
    }


}

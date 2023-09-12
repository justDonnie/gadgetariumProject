package peaksoft.services.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.ProductRequest;
import peaksoft.dto.ProductResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.BadCredentialException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Product;
import peaksoft.repositories.ProductRepository;
import peaksoft.services.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public SimpleResponse saveProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setImages(productRequest.images());
        product.setCharacteristic(productRequest.characteristic());
        product.setMadeIn(productRequest.madeIn());
        product.setCategory(productRequest.category());
        productRepository.save(product);
        log.info("Product with ID: "+product.getId()+" is successfully saved !!!");
        return new SimpleResponse(
                HttpStatus.OK,
                "Product with ID: "+product.getId()+" is successfully saved !!!"
        );
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        try {
            return productRepository.getAllProducts();
        } catch (NotFoundException e) {
            log.info("There are no any products at database !!!");
            throw new NotFoundException("There are no any products at database !!!");
        }
    }


    @Override
    public ProductResponse findProductById(Long prodId) {
        return productRepository.findProductById(prodId)
                .orElseThrow(()->{
                    String massage = "Product with ID : " + prodId + " is not found!";
                    log.error(massage);
                    return   new NotFoundException(massage);
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
        if (!productRepository.existsById(productId)){
            throw new NotFoundException("Product with ID: "+productId+" is not found!!!");
        }
        productRepository.deleteById(productId);
        log.info("Product is successfully deleted!!!");
        return new SimpleResponse(
                HttpStatus.OK,
                "Product with ID: "+productId+" is successfully deleted !!!"
        );
    }


    @Override
    public ProductResponse getProductWithComment(Long productId) {
        ProductResponse productResponse = productRepository.getProductWithComment(productId)
                .orElseThrow(()-> new NotFoundException("Product with ID :"+productId+" is not found !!!"));
        List<String> comment = productRepository.comment(productId);
        List<String> images = productRepository.images(productId);
        int countFavorite = productRepository.countFavorite(productId);
        productResponse.setImages(images);
        productResponse.setComment(comment);
        productResponse.setCountFavorite(countFavorite);
        return productResponse;
    }
}

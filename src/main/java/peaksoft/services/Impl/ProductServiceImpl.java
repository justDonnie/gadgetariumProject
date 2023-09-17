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
import peaksoft.models.Product;
import peaksoft.repositories.BrandRepository;
import peaksoft.repositories.CommentRepository;
import peaksoft.repositories.ProductRepository;
import peaksoft.services.ProductService;

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
        List<ProductResponse>allResponses=productRepository.getAllProducts();
        for (ProductResponse p :allResponses) {
            p.setBrandName(productRepository.getBrandName(p.getId()));
            p.setBrandImage(productRepository.getBrandImage(p.getId()));
            p.setImages(productRepository.getImages(p.getId()));
            p.setComments(productRepository.getComments(p.getId()));
            productRepository.countFavorite(p.getId());
        }
        return allResponses;
    }



    @Override
    public ProductResponse findProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("There are no any products in database with ID: " + productId));
        ProductResponse productResponse = new ProductResponse();
        productResponse.setBrandName(productRepository.getBrandName(productId));
        productResponse.setBrandImage(productRepository.getBrandImage(productId));
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setImages(productRepository.getImages(productId));
        productResponse.setCharacteristic(product.getCharacteristic());
        productResponse.setMadeIn(product.getMadeIn());
        productResponse.setCategory(product.getCategory());
        productResponse.setComments(productRepository.getComments(productId));
        productResponse.setCountFavorite(productRepository.countFavorite(productId));
        return productResponse;
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
        if (ascOrDesc.equalsIgnoreCase("asc") || (ascOrDesc.equalsIgnoreCase("desc"))) {
            if (ascOrDesc.equalsIgnoreCase("asc")) {
                List<ProductResponse> allProducts = productRepository.getProductByCategoryAndPriceDesc(category);
                for (ProductResponse p : allProducts) {
                    p.setBrandName(productRepository.getBrandName(p.getId()));
                    p.setBrandImage(productRepository.getBrandImage(p.getId()));
                    p.setImages(productRepository.getImages(p.getId()));
                    p.setComments(productRepository.getComments(p.getId()));
                    productRepository.countFavorite(p.getId());
                }
                return allProducts;
            } else if (ascOrDesc.equalsIgnoreCase("desc")) {
                List<ProductResponse> allProducts = productRepository.getProductByCategoryAndPriceAsc(category);
                for (ProductResponse p : allProducts) {
                    p.setBrandName(productRepository.getBrandName(p.getId()));
                    p.setBrandImage(productRepository.getBrandImage(p.getId()));
                    p.setImages(productRepository.getImages(p.getId()));
                    p.setComments(productRepository.getComments(p.getId()));
                    productRepository.countFavorite(p.getId());
                }
                return allProducts;
            }
        }else {
            throw new NotFoundException("Input the correct command !!!");
        }
        return null;
    }

    @Override
    public ProductResponse findProductByComments(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("There are no any products in database with ID: " + productId));
        ProductResponse productResponse = new ProductResponse();
        productResponse.setBrandName(productRepository.getBrandName(productId));
        productResponse.setBrandImage(productRepository.getBrandImage(productId));
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setImages(productRepository.getImages(productId));
        productResponse.setCharacteristic(product.getCharacteristic());
        productResponse.setMadeIn(product.getMadeIn());
        productResponse.setCategory(product.getCategory());
        productResponse.setComments(productRepository.getComments(productId));
        productResponse.setCountFavorite(productRepository.countFavorite(productId));
        return productResponse;

    }
}

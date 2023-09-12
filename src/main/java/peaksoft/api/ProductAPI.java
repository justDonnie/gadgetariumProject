package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.ProductRequest;
import peaksoft.dto.ProductResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Products API")
public class ProductAPI {
    private final ProductService productService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public SimpleResponse saveProduct(@RequestBody ProductRequest productRequest){
        return productService.saveProduct(productRequest);
    }

    @PermitAll
    @GetMapping("/getAll")
    public List<ProductResponse>getAllProducts(){
        return productService.getAllProducts();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/findById/{prodId}")
    public ProductResponse findProductById(@PathVariable Long prodId){
        return productService.findProductById(prodId);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{prodId}")
    public SimpleResponse updateProductById(@PathVariable Long prodId,
                                            @RequestBody ProductRequest productRequest){
        return productService.updateProduct(prodId,productRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{prodId}")
    public SimpleResponse deleteProductById(@PathVariable Long prodId){
        return  productService.deleteProduct(prodId);
    }

    @GetMapping("/getWithComment/{prodId}")
    public ProductResponse getProductsWithComment(@PathVariable Long prodId){
        return productService.getProductWithComment(prodId);
    }

}

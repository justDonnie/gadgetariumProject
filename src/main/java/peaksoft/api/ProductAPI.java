package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.*;
import peaksoft.enums.Category;
import peaksoft.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Products API")
public class ProductAPI {
    private final ProductService productService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/{brandId}")
    public SimpleResponse saveProduct(@RequestBody ProductRequest productRequest,
                                      @PathVariable Long brandId){
        return productService.saveProduct(productRequest,brandId);
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


    @PermitAll
    @GetMapping("/sorted/{ascOrDesc}/{category}")
    public List<ProductResponse>getProductByCategoryAndPrice(@PathVariable String ascOrDesc,
                                                             @PathVariable Category category){
        return productService.getProductByCategoryAndPrice(ascOrDesc,category);
    }

    @PermitAll
    @GetMapping("/getWithComment/{prodId}")
    public ProductResponse findProductByComments(@PathVariable Long prodId){
        return productService.findProductByComments(prodId);
    }

}

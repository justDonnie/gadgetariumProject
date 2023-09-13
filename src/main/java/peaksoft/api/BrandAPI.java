package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.BrandRequest;
import peaksoft.dto.BrandResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.services.BrandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brands")
@Tag(name = "Brand API")
public class BrandAPI {
    private final BrandService brandService;


    @PermitAll
    @GetMapping
    public List<BrandResponse> getAllBrands(){
        return brandService.getAllBrands();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveBrand")
    public SimpleResponse saveBrand(@RequestBody BrandRequest brandRequest){
        return brandService.saveBrand(brandRequest);
    }
    @PermitAll
    @GetMapping("/findById/{brandId}")
    public BrandResponse findBrandById(@PathVariable Long brandId){
        return brandService.findBrandById(brandId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{brandId}")
    public SimpleResponse updateBrand(@PathVariable Long brandId,
                                      @RequestBody BrandRequest brandRequest){
        return brandService.updateBrand(brandId,brandRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{brandId}")
    public SimpleResponse deleteBrand(@PathVariable Long brandId){
        return brandService.deleteBrand(brandId);
    }




}

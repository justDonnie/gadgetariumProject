package peaksoft.services.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.BrandRequest;
import peaksoft.dto.BrandResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Brand;
import peaksoft.models.Product;
import peaksoft.repositories.BrandRepository;
import peaksoft.repositories.ProductRepository;
import peaksoft.services.BrandService;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    @Override
    public SimpleResponse saveBrand(BrandRequest brandRequest, Long productId) {
        Product product = productRepository.
                findById(productId).
                orElseThrow(() -> new NotFoundException("Product with ID: " + productId + " is not found !!!"));
        Brand brand = new Brand();
        brand.setBrandName(brandRequest.brandName());
        brand.setImage(brandRequest.image());
        brandRepository.save(brand);
        product.setBrand(brand);
        return new SimpleResponse(
                HttpStatus.OK,
                "Brand: "+brand.getBrandName()+" - is successfully saved !!!"
        );
    }

    @Override
    public List<BrandResponse> getAllBrands(Long productId) {
        return null;
    }

    @Override
    public BrandResponse findBrandById(Long brandId, Long productId) {
        return null;
    }

    @Override
    public BrandResponse updateBrand(Long brandId, BrandRequest brandRequest) {
        return null;
    }

    @Override
    public SimpleResponse deleteBrand(Long brandId) {
        return null;
    }
}

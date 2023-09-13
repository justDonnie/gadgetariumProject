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
    public SimpleResponse saveBrand(BrandRequest brandRequest) {
        Brand brand = new Brand();
        brand.setBrandName(brandRequest.brandName());
        brand.setImage(brandRequest.image());
        brandRepository.save(brand);
        return new SimpleResponse(
                HttpStatus.OK,
                "Brand: " + brand.getBrandName() + " - is successfully saved !!!"
        );
    }

    @Override
    public List<BrandResponse> getAllBrands() {
        return brandRepository.getAllBrands();
    }

    @Override
    public BrandResponse findBrandById(Long brandId) {
        return brandRepository.findBrandById(brandId)
                .orElseThrow(() -> {
                    String massage = "Product with ID : " + brandId + " is not found!";
                    log.error(massage);
                    return new NotFoundException(massage);
                });
    }

    @Override
    public SimpleResponse updateBrand(Long brandId, BrandRequest brandRequest) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> {
            String massage = "Brand with ID : " + brandId + " is not found!";
            log.error(massage);
            return new NotFoundException(massage);
        });
        brand.setBrandName(brandRequest.brandName());
        brand.setImage(brand.getImage());
        brandRepository.save(brand);
        log.info("Brand is successfully updated !!!");
        return new SimpleResponse(
                HttpStatus.OK,
                "Brand is successfully updated!!!"
        );
    }
        @Override
        public SimpleResponse deleteBrand (Long brandId){
        if (!brandRepository.existsById(brandId)){
            throw new NotFoundException("Brand with ID : " + brandId + " is not found!");
        }
        brandRepository.deleteById(brandId);
        log.info("Brand is successfully deleted !!!");
            return new SimpleResponse(
                    HttpStatus.OK,
                    "Brand: "+brandId+" is successfully deleted !!!"
            );
        }
    }

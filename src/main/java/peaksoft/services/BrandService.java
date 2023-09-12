package peaksoft.services;

import peaksoft.dto.*;

import java.util.List;

public interface BrandService {

    SimpleResponse saveBrand(BrandRequest brandRequest, Long productId);

    List<BrandResponse> getAllBrands(Long productId);

    BrandResponse findBrandById(Long brandId,Long productId);

    BrandResponse updateBrand(Long brandId, BrandRequest brandRequest);

    SimpleResponse deleteBrand(Long brandId);

}

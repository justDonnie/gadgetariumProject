package peaksoft.services;

import peaksoft.dto.*;

import java.util.List;

public interface BrandService {

    SimpleResponse saveBrand(BrandRequest brandRequest);

    List<BrandResponse> getAllBrands();

    BrandResponse findBrandById(Long brandId);

    SimpleResponse updateBrand(Long brandId, BrandRequest brandRequest);

    SimpleResponse deleteBrand(Long brandId);

}

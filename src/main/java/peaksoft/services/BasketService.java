package peaksoft.services;

import peaksoft.dto.BrandRequest;
import peaksoft.dto.BrandResponse;
import peaksoft.dto.SimpleResponse;

import java.util.List;

public interface BasketService {

    SimpleResponse saveBasket(List<Long> productId);


}

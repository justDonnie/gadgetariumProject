package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.BrandResponse;
import peaksoft.models.Brand;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("select new peaksoft.dto.BrandResponse(b.id,b.brandName,b.image)from Brand b")
    List<BrandResponse> getAllBrands();

    Optional<BrandResponse>findBrandById(Long brandId);

}
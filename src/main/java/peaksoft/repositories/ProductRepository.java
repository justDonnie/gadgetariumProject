package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.GetAllResponse;
import peaksoft.dto.ProductResponse;
import peaksoft.enums.Category;
import peaksoft.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select new peaksoft.dto.ProductResponse(p.id, p.name, p.price, p.characteristic, p.isFavorite, p.madeIn, p.category) from Product p ")
    List<ProductResponse> getAllProducts();


    @Query("select new peaksoft.dto.ProductResponse(p.id, p.name, p.price, p.characteristic, p.isFavorite, p.madeIn, p.category)from Product p where p.id=:prodId")
    Optional<ProductResponse>findProductById(Long prodId);

    @Query("select new peaksoft.dto.GetAllResponse(p.id,p.name,p.price,p.characteristic,p.isFavorite,p.madeIn,p.category)from Product p")
    List<GetAllResponse>findAllProduct();
//this.id = id;
//        this.name = name;
//        this.price = price;
//        this.characteristic = characteristic;
//        this.isFavorite = isFavorite;
//        this.madeIn = madeIn;
//        this.category = category;
//        this.brand = brand;

    @Query("select p.images from Product p join p.images i")
    List<String>getImage();

    @Query("select cast(count (f) as int) from Product p join p.favorites f where p.id = :productId")
    int countFavorite(Long productId);


    @Query("select new peaksoft.dto.ProductResponse(p.id, p.name, p.price, p.characteristic, p.isFavorite, p.madeIn, p.category)from Product p where p.category=:category order by p.price asc")
   List<ProductResponse> getProductByCategoryAndPriceAsc(Category category);

    @Query("select new peaksoft.dto.ProductResponse(p.id, p.name, p.price, p.characteristic, p.isFavorite, p.madeIn, p.category)from Product p where p.category=:category order by p.price desc")
    List<ProductResponse> getProductByCategoryAndPriceDesc(Category category);

}
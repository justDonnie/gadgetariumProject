package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.CommentResponse;
import peaksoft.dto.GetAllResponse;
import peaksoft.dto.ProductResponse;
import peaksoft.enums.Category;
import peaksoft.models.Comment;
import peaksoft.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select new peaksoft.dto.ProductResponse(p.id,p.name,p.price, p.characteristic,p.madeIn,p.category) from Product p ")
    List<ProductResponse> getAllProducts();


    @Query("select new peaksoft.dto.ProductResponse(p.id,p.name,p.price, p.characteristic,p.madeIn,p.category)from Product p where p.id=:prodId")
    Optional<ProductResponse>findProductById(Long prodId);

    Optional<Product>getProductById(Long prodId);


    @Query("select new peaksoft.dto.CommentResponse(c.id,c.comment,c.createdAt,c.updatedAt) from Comment c join c.product.id p where c.product.id =:prodId")
    List<CommentResponse>getCommentOfProducts(Long prodId);

    @Query(value = "select c.user_id,c.comment from comments c join products p on c.product_id=p.id where p.id=?1 ", nativeQuery = true)
    List<String>getComments(Long prodId); //create counter of commentators


    @Query(value = "select images from product_images where product_id = ?1", nativeQuery = true)
    List<String> getImages(Long productId);

    @Query(value = "select b.brand_name from products p join brands b on p.brand_id=b.id where p.id=?1",nativeQuery = true)
    String getBrandName(Long productId);

    @Query(value = "select b.image from products p join brands b on p.brand_id=b.id where p.id=?1",nativeQuery = true)
    String getBrandImage(Long productId);



    @Query("select cast(count (f) as int) from Product p join p.favorites f where p.id = :productId")
    int countFavorite(Long productId);


    @Query("select new peaksoft.dto.ProductResponse(p.id,p.name,p.price, p.characteristic,p.madeIn,p.category)from Product p where p.category=:category order by p.price asc")
    List<ProductResponse> getProductByCategoryAndPriceAsc(Category category);

    @Query("select new peaksoft.dto.ProductResponse(p.id,p.name,p.price, p.characteristic,p.madeIn,p.category)from Product p where p.category=:category order by p.price desc")
    List<ProductResponse> getProductByCategoryAndPriceDesc(Category category);

}
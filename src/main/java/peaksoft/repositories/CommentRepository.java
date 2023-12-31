package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.CommentResponse;
import peaksoft.models.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select new peaksoft.dto.CommentResponse(c.id,c.comment,c.createdAt,c.updatedAt)from Comment c join c.product p on c.product.id=p.id where p.id=:productId")
    List<CommentResponse> getAllCommentsOfProducts(Long productId);

    @Query("select new peaksoft.dto.CommentResponse(c.id,c.comment,c.createdAt,c.updatedAt)from Comment c join c.product p on c.product.id=p.id where p.id=:productId and c.id=:commentId")
    Optional<CommentResponse>getCommentByIdFromProduct(Long productId,Long commentId);

//    @Query("select c from Comment c join c.product on c.product.id=:productId")
//    List<ProductWithCommentsResponse> findAllByProductId(Long productId);
}
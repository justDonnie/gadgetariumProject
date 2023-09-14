package peaksoft.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Comment;

import java.util.List;
@Setter
@Getter
@Builder
public class ProductWithCommentsResponse {
    private ProductResponse productResponse;
    private List<Comment> comments;

    public ProductWithCommentsResponse(ProductResponse productResponse, List<Comment> comments) {
        this.productResponse = productResponse;
        this.comments = comments;
    }
}

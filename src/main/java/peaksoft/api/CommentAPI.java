package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.CommentRequest;
import peaksoft.dto.CommentResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.services.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
@Tag(name = "Comment API")
public class CommentAPI {
    private final CommentService commentService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/create/{productId}")
    public CommentResponse createComment(@RequestBody CommentRequest commentRequest,
                                         @PathVariable Long productId){
        return commentService.createComment(commentRequest,productId);
    }

    @PermitAll
    @GetMapping("/getAll/{productId}")
    public List<CommentResponse> getAllCommentsOfProducts(@PathVariable Long productId){
        return commentService.getAllComments(productId);
    }

    @PermitAll
    @GetMapping("/byId/{productId}/{commentId}")
    public CommentResponse getCommentByIdFromProduct(@PathVariable Long productId,
                                                     @PathVariable Long commentId){
        return commentService.findCommentById(commentId,productId);
    }

    @PermitAll
    @DeleteMapping("/delete/{commentId}/{productId}")
    public SimpleResponse deleteComment(@PathVariable Long commentId,
                                        @PathVariable Long productId){
        return commentService.deleteComment(commentId,productId);
    }



}

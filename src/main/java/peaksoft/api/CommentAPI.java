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

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getAll/{productId}")
    public List<CommentResponse> getAllCommentsOfProducts(@PathVariable Long productId){
        return commentService.getAllComments(productId);
    }

    @PermitAll
    @GetMapping("/byId/{productId}/{commentId}")
    public CommentResponse getCommentByIdFromProduct(@PathVariable Long commentId,
                                                     @PathVariable Long productId){
        return commentService.findCommentById(commentId,productId);
    }
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/update/{commentId}")
    public CommentResponse updateComment(@PathVariable Long commentId,
                                         @RequestBody CommentRequest commentRequest){
        return commentService.updateComment(commentId,commentRequest);
    }
    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/delete/{commentId}")
    public SimpleResponse deleteComment(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }



}

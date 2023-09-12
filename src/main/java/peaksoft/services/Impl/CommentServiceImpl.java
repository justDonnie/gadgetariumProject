package peaksoft.services.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.CommentRequest;
import peaksoft.dto.CommentResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.BadCredentialException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Comment;
import peaksoft.models.Product;
import peaksoft.models.User;
import peaksoft.repositories.CommentRepository;
import peaksoft.repositories.ProductRepository;
import peaksoft.repositories.UserRepository;
import peaksoft.services.CommentService;

import java.time.ZonedDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public CommentResponse createComment(CommentRequest commentRequest, Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email)
                .orElseThrow(() -> new BadCredentialException("There are no any Users with email: " + email + " !!!"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product with ID: " + productId + " is not found !!!"));
        Comment comment = new Comment();
        comment.setComment(commentRequest.comment());
        comment.setCreatedAt(ZonedDateTime.now());
        comment.setProduct(product);
        Comment comment1 = commentRequest.build();
        comment1.addComment(comment);
        comment1.setProduct(product);
        comment1.setUser(user);
        commentRepository.save(comment1);
        log.info("Comment is successfully created !!!");
        return CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .createdAt(ZonedDateTime.now())
                .build();
    }

    @Override
    public List<CommentResponse> getAllComments(Long productId) {
        try {
            return commentRepository.getAllCommentsOfProducts(productId);
        } catch (NotFoundException e) {
            log.info("There are no any comments at database !!!");
            throw new NotFoundException("There are no any comments at database !!!");
        }

    }

    @Override
    public CommentResponse findCommentById(Long commentId, Long productId) {
        return commentRepository.getCommentByIdFromProduct(commentId,productId)
                .orElseThrow(()-> new NotFoundException("Input correct command, comment with ID: "+commentId+" is not found !!!"));
    }

    @Override
    public CommentResponse updateComment(Long commentId, CommentRequest newCommentRequest) {
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new NotFoundException("Input correct command, comment with ID: " + commentId + " is not found !!!"));
        comment.setComment(newCommentRequest.comment());
        comment.setUpdatedAt(ZonedDateTime.now());
        commentRepository.save(comment);
        log.info("Comment is successfully updated !!!");
        return CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .updatedAt(ZonedDateTime.now())
                .build();
    }

    @Override
    public SimpleResponse deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Input correct command, comment with ID: " + commentId + " is not found !!!"));
        if (!commentRepository.existsById(commentId)){
            throw new BadCredentialException("There are no any comment in database!!!");
        }
//        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Input correct command, product with ID: " + productId + " is not found !!!"));
//        if (!commentRepository.existsById(commentId)){
//            throw new BadCredentialException("There are no any products in database!!!");
//
//            product.setComments(comment);
        commentRepository.delete(comment);
        return new SimpleResponse(
                HttpStatus.OK,
                "Comment with ID: "+commentId+" is successfully deleted !!!"
        );
    }
}

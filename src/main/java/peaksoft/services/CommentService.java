package peaksoft.services;


import peaksoft.dto.CommentRequest;
import peaksoft.dto.CommentResponse;
import peaksoft.dto.SimpleResponse;

import java.util.List;


public interface CommentService {

    CommentResponse createComment(CommentRequest commentRequest,Long productId);

    List<CommentResponse> getAllComments(Long productId);

    CommentResponse findCommentById(Long commentId,Long productId);

    CommentResponse updateComment(Long commentId, CommentRequest newCommentRequest);

    SimpleResponse deleteComment(Long commentId);
}

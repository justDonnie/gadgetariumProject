package peaksoft.dto;

import lombok.Builder;
import peaksoft.models.Comment;

import java.time.ZonedDateTime;

@Builder
public record CommentRequest(
        String comment,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt) {

    public Comment build() {
        Comment comment1 = new Comment();
        comment1.setComment(this.comment);
        comment1.setCreatedAt(ZonedDateTime.now());
        comment1.setUpdatedAt(ZonedDateTime.now());
        return comment1;

    }
}

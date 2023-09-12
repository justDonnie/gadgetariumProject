package peaksoft.dto;

import lombok.Builder;

import java.time.ZonedDateTime;
@Builder
public record CommentResponse(
        Long id,
        String comment,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
    public CommentResponse {
    }
}

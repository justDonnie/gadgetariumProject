package peaksoft.dto;

import lombok.Builder;

import java.time.ZonedDateTime;
@Builder
public record UserResponse(
         Long id,
         String firstName,
         String lastName,
         String email,
         ZonedDateTime createdAt,
         ZonedDateTime updatedAt
) {
    public UserResponse {
    }
}

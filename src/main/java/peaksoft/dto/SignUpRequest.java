package peaksoft.dto;

import lombok.Builder;

@Builder
public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String password) {
}

package peaksoft.dto;

import lombok.*;

@Builder
public record SignInRequest(
        String email,
        String password
) {
}

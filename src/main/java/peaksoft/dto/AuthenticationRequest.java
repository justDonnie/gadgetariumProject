package peaksoft.dto;

import lombok.Builder;

@Builder
public record AuthenticationRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}

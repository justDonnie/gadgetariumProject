package peaksoft.dto;


import lombok.Builder;


@Builder
public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password) {
}

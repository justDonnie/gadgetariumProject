package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;
import peaksoft.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User API")


public class UserAPI {
    private final UserService userService;

    @PermitAll
    @GetMapping("/getAll")
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get/{userId}")
    public UserResponse findUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{userId}")
    public SimpleResponse updateUser(@PathVariable Long userId,
                                     @RequestBody UserRequest userRequest){
        return userService.updateUser(userId,userRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public SimpleResponse deleteUserById(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

}

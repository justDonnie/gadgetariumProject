package peaksoft.services;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long userId);

    SimpleResponse updateUser(Long userId, UserRequest newUserRequest);

    SimpleResponse deleteUser(Long userId);

}

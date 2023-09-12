package peaksoft.services.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.User;
import peaksoft.repositories.UserRepository;
import peaksoft.services.UserService;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public UserResponse getUserById(Long userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(()->{
                    String massage = "User with Id: " + userId + " is not found!";
                    log.error(massage);
                    return   new NotFoundException(massage);
                });
    }

    @Override
    public SimpleResponse updateUser(Long userId, UserRequest newUserRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            String massage = "User with Id: " + userId + " is not found!";
            log.error(massage);
            return new NotFoundException(massage);
        });
        user.setFirstName(newUserRequest.firstName());
        user.setLastName(newUserRequest.lastName());
        user.setEmail(newUserRequest.email());
        user.setPassword(newUserRequest.password());
        user.setUpdatedAt(ZonedDateTime.now());
        userRepository.save(user);
        log.info("User with ID"+userId+" is successfully updated !!!");
        return new SimpleResponse(
                HttpStatus.OK,
                "User with ID "+user.getId()+" is successfully updated !!!"
        );
    }

    @Override
    public SimpleResponse deleteUser(Long userId) {
        if (!userRepository.existsById(userId)){
            throw new NotFoundException("User with ID " + userId + " is not found!!!");
        }
        userRepository.deleteById(userId);
        log.info(" User is successfully deleted!!!");
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("User with ID "+userId+" is successfully deleted !!!")
        ) ;
    }
}

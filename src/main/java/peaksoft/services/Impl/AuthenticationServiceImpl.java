package peaksoft.services.Impl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.AuthenticationResponse;
import peaksoft.dto.SignInRequest;
import peaksoft.dto.SignUpRequest;
import peaksoft.enums.Role;
import peaksoft.exceptions.AlreadyExistException;
import peaksoft.exceptions.BadCredentialException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.User;
import peaksoft.repositories.UserRepository;
import peaksoft.security.jwt.JwtService;
import peaksoft.services.AuthenticationService;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userRepository.existsByEmail(signUpRequest.email())) {
            throw new AlreadyExistException(
                    "User with email " + signUpRequest.email() + " is already exists!"
            );
        }
        User user = User.builder()
                .firstName(signUpRequest.firstName())
                .lastName(signUpRequest.lastName())
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .createdAt(ZonedDateTime.now())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        System.out.println("User with Id: "+user.getId()+" is successfully registered !!!");
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.email())
                .orElseThrow(() -> new NotFoundException(
                        "User with  email " + signInRequest.email() + " is not found"
                ));
        if (signInRequest.email().isBlank()) {
            throw new BadCredentialException("Email is blanked!!!");
        }
        if (!passwordEncoder.matches(signInRequest.password(),user.getPassword())) {
            throw new BadCredentialException("Wrong password!!!");
        }
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @PostConstruct
    @Override
    public void initMethod() {
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Adminov");
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin1"));
        user.setRole(Role.ADMIN);
        user.setCreatedAt(ZonedDateTime.now());
        if (!userRepository.existsByEmail("admin@gmail.com")){
            userRepository.save(user);
        }
    }
}

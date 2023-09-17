package peaksoft.services.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.enums.Role;
import peaksoft.exceptions.AccessDeniedException;
import peaksoft.exceptions.BadCredentialException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Basket;
import peaksoft.models.Product;
import peaksoft.models.User;
import peaksoft.repositories.BasketRepository;
import peaksoft.repositories.ProductRepository;
import peaksoft.repositories.UserRepository;
import peaksoft.services.BasketService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    @Override
    public SimpleResponse saveBasket(List<Long> productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Authentication required to create a comment !!!");
        }
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email)
                .orElseThrow(() -> new BadCredentialException("There are no any Users with email: " + email + " !!!"));
        List<Product> products = new ArrayList<>();

       if(user.getBasket()==null) {
           Basket basket = new Basket();
           basket.setUser(user);
           basket.setProducts(products);
           user.setBasket(basket);
           basketRepository.save(basket);
       }
//        for (Products l : productId) {
//            user.getBasket().getProducts().add(productRepository.findById(l).orElseThrow(
//                    () -> new NotFoundException("User with id: %s not found".formatted(l))
//            ));
//        }

        log.info("Basket successfully saved !!!");
        return SimpleResponse
                .builder()
                .message("Products successfully saved")
                .httpStatus(HttpStatus.OK)
                .build();
    }

}


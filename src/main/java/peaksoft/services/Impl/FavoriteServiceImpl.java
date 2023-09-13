package peaksoft.services.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Product;
import peaksoft.models.User;
import peaksoft.repositories.FavoriteRepository;
import peaksoft.repositories.ProductRepository;
import peaksoft.repositories.UserRepository;
import peaksoft.services.FavoriteService;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteServiceImpl implements FavoriteService {
//    private final FavoriteRepository favoriteRepository;
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;
//
//    @Override
//    public SimpleResponse createFav(Long productId, FavoriteRequest favoriteRequest) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException("There no any User with email: " + email));
//        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product with ID: " + productId + " is not found!!!"));
//        return null;
//    }
}

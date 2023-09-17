package peaksoft.services.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Favorite;
import peaksoft.models.Product;
import peaksoft.models.User;
import peaksoft.repositories.FavoriteRepository;
import peaksoft.repositories.ProductRepository;
import peaksoft.repositories.UserRepository;
import peaksoft.services.FavoriteService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public SimpleResponse createFav(Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException("There no any User in database with email: " + email));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product with ID: " + productId + " is not found!!!"));
        List<Favorite> favorites=favoriteRepository.findAll();
        for (Favorite f: favorites) {
            if(f.getUser().equals(user) && f.getProduct().equals(product)){
                favoriteRepository.deleteById(f.getId());
                return new SimpleResponse(
                        HttpStatus.OK,
                        user+" LIKED product with ID: " + f.getId() + " !!!");
            }
        }
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        product.getFavorites().size();
        favoriteRepository.save(favorite);
        return new SimpleResponse(
                HttpStatus.OK,
                user+" LIKED product with ID: " + favorite.getId() + " !!!");
    }
}










// @Override
//    public SimpleResponse createFav(Long productId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException("There no any User in database with email: " + email));
//        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product with ID: " + productId + " is not found!!!"));
//        List<Favorite> favorites=favoriteRepository.findAll();
//        for (Favorite f: favorites) {
//            if(f.getUser().equals(user) && f.getProduct().equals(product)){
//                favoriteRepository.deleteById(f.getId());
//                return new SimpleResponse(
//                        HttpStatus.OK,
//                        user+" LIKED product with ID: " + f.getId() + " !!!");
//            }
//        }
//        Favorite favorite = new Favorite();
//        favorite.setUser(user);
//        favorite.setProduct(product);
//        product.getFavorites().size();
//        favoriteRepository.save(favorite);
//        return new SimpleResponse(
//                HttpStatus.OK,
//                "Like with ID: " + favorite.getId() + " is successfully dropped !!!");
//    }

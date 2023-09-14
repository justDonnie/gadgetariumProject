package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.SimpleResponse;
import peaksoft.services.FavoriteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorite")
@Tag(name = "Favorite API")
public class FavoriteAPI {
    private final FavoriteService favoriteService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/like/{prodId}")
    public SimpleResponse createFavorite(@PathVariable Long prodId){
        return favoriteService.createFav(prodId);
    }
}

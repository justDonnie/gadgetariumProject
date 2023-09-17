package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.services.BasketService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Tag(name = "Basket API")
public class BasketAPI {

    private final BasketService basketService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public SimpleResponse saveBasket(@RequestParam List<Long>prodId){
        return basketService.saveBasket(prodId);
    }


}

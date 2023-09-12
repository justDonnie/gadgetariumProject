package peaksoft.services.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.repositories.FavoriteRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteServiceImpl {
    private final FavoriteRepository favoriteRepository;



}

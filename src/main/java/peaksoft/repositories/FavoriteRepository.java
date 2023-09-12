package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.models.Favorite;
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
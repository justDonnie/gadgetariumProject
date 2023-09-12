package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.models.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
}
package de.telran.eshopproject.repository;

import de.telran.eshopproject.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CartRepository extends JpaRepository<ShoppingCart, Long> {
    public Optional<ShoppingCart> findByUserId(String user_email);
}

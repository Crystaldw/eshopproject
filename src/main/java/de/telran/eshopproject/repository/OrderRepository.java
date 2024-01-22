package de.telran.eshopproject.repository;

import de.telran.eshopproject.model.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem, Long> {

    public List<OrderItem> findByCart_Id(Long cart_id);

    public Optional<OrderItem>findById(String id);
}

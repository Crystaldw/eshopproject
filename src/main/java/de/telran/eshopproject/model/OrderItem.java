package de.telran.eshopproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    private String id;

    @OneToOne
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false )
    @JsonIgnore
    private ShoppingCart cart;

    private int quantity;

    public void fromDto (Product p, ShoppingCart cart, int quantity){
        id = UUID.randomUUID().toString().replace("-", "");
        this.product = p;
        this.cart = cart;
        this.quantity = quantity;
    }
}

package de.telran.eshopproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Модель элемента заказа в системе электронной коммерции.
 */

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    //Уникальный идентификатор элемента заказа, генерируется с использованием UUID.
    @Id
    private String id;

    //Продукт, связанный с этим элементом заказа.
    @OneToOne
    private Product product;

    //Корзина покупок, связанная с этим элементом заказа.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false )
    @JsonIgnore
    private ShoppingCart cart;

    //Количество продуктов в этом элементе заказа.
    private int quantity;

    /**
     * Заполняет поля элемента заказа данными из объекта Product, корзины покупок и количества.
     *
     * @param p        Продукт для элемента заказа.
     * @param cart     Корзина покупок для элемента заказа.
     * @param quantity Количество продуктов в элементе заказа.
     */

    public void fromDto (Product p, ShoppingCart cart, int quantity){
        id = UUID.randomUUID().toString().replace("-", "");
        this.product = p;
        this.cart = cart;
        this.quantity = quantity;
    }
}

package de.telran.eshopproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель продукта в системе электронной коммерции.
 */

@Entity
@Table(name = "clothes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /**
     * Перечисление, представляющее пол продукта.
     */
    public enum sex {
        MALE, FEMALE
    }

    //Уникальный идентификатор продукта, генерируется автоматически базой данных.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Пол продукта, представлен в виде перечисления sex.
    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private sex a_sex;

    //Тип платья продукта.
    @Column(name = "dress type")
    private String b_dressType;

    //Ссылка на изображение продукта.
    @Column(name = "image")
    private String c_image;

    //Цена продукта
    @Column(name = "price")
    private String d_price;

    //Информация о поступлении продукта (новый, старый и т. д.).
    @Column(name = "arrival")
    private String e_arrival;

    //Скидка на продукт.
    @Column(name = "discount")
    private int f_discount;

}

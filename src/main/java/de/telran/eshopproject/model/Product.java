package de.telran.eshopproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clothes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    public enum sex {
        MALE, FEMALE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private sex a_sex;

    @Column(name = "dress type")
    private String b_dressType;

    @Column(name = "image")
    private String c_image;

    @Column(name = "price")
    private double d_price;

    @Column(name = "arrival")
    private String e_arrival;

    @Column(name = "discount")
    private String f_discount;

}

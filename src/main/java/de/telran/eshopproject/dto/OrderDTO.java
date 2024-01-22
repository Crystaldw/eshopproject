package de.telran.eshopproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Объект передачи данных (DTO) для информации о заказе.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long productId;
    private int quantity;
}

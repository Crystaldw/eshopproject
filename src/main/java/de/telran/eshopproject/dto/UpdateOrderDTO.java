package de.telran.eshopproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Объект передачи данных (DTO) для запроса на обновление заказа.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDTO {

    private String id;
    private Integer quantity;

}

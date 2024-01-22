package de.telran.eshopproject.controllers;

import de.telran.eshopproject.dto.OrderDTO;
import de.telran.eshopproject.dto.UpdateOrderDTO;
import de.telran.eshopproject.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Контроллер для управления корзиной покупок.
 */

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class CartServiceController {

    private final CartService cartService;

    /**
     * Обрабатывает запрос на добавление продукта в корзину.
     *
     * @param orderDTO Информация о заказе.
     * @return ResponseEntity с результатом операции и HTTP-статусом 201 (CREATED).
     *
     * HTTP-метод: POST
     * Endpoint: /api/v1/add
     * Назначение:
     * Обрабатывает запрос на добавление продукта в корзину.
     * Принимает объект OrderDTO из тела запроса.
     * Возвращает ResponseEntity с результатом операции и HTTP-статусом 201 (CREATED).
     */
    @PostMapping (path = "add")
    public ResponseEntity<String> addProduct(@RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<String>(cartService.addToCart(orderDTO),HttpStatus.CREATED);
    }

    /**
     * Возвращает информацию о продуктах в корзине.
     *
     * @return ResponseEntity с данными о продуктах и HTTP-статусом 200 (OK).
     *
     * HTTP-метод: GET
     * Endpoint: /api/v1/orders
     * Назначение:
     * Возвращает информацию о продуктах в корзине.
     * Возвращает ResponseEntity с данными о продуктах и HTTP-статусом 200 (OK).
     */

    @GetMapping(path = "orders")
    public ResponseEntity<HashMap<String, Object>>getOrders(){
        return new ResponseEntity<>(cartService.getProducts(), HttpStatus.OK);
    }

    /**
     * Обрабатывает запрос на обновление количества продуктов в заказе в корзине.
     *
     * @param updateOrderDTO Информация для обновления заказа.
     * @return ResponseEntity с результатом операции и HTTP-статусом 201 (CREATED).
     *
     * HTTP-метод: PUT
     * Endpoint: /api/v1/order
     * Назначение:
     * Обрабатывает запрос на обновление количества продуктов в заказе в корзине.
     * Принимает объект UpdateOrderDTO из тела запроса.
     * Возвращает ResponseEntity с результатом операции и HTTP-статусом 201 (CREATED).
     */

    @PutMapping(path = "order")
    public ResponseEntity<String> updateOrder(@RequestBody UpdateOrderDTO updateOrderDTO) {
        return new ResponseEntity<>(cartService.updateOrder(updateOrderDTO), HttpStatus.CREATED);
    }

    /**
     * Обрабатывает запрос на удаление заказа из корзины.
     *
     * @param id Идентификатор заказа для удаления.
     * @return ResponseEntity с результатом операции и HTTP-статусом 204 (NO_CONTENT).
     *
     * HTTP-метод: DELETE
     * Endpoint: /api/v1/order/{id}
     * Назначение:
     * Обрабатывает запрос на удаление заказа из корзины.
     * Принимает идентификатор заказа из пути запроса.
     * Возвращает ResponseEntity с результатом операции и HTTP-статусом 204 (NO_CONTENT).
     */

    @DeleteMapping(path = "order/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) {
        return new ResponseEntity<>(cartService.deleteOrder(id), HttpStatus.NO_CONTENT);
    }
}

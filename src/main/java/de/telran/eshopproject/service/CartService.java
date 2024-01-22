package de.telran.eshopproject.service;

import de.telran.eshopproject.dto.OrderDTO;
import de.telran.eshopproject.dto.UpdateOrderDTO;
import de.telran.eshopproject.model.OrderItem;
import de.telran.eshopproject.model.Product;
import de.telran.eshopproject.model.ShoppingCart;
import de.telran.eshopproject.repository.CartRepository;
import de.telran.eshopproject.repository.OrderRepository;
import de.telran.eshopproject.repository.ProductRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;

/**
 * Сервис управления корзиной покупок. Обеспечивает функциональность добавления, обновления и удаления продуктов в корзине,
 * а также получение содержимого корзины для аутентифицированных пользователей.
 */
@Service
public class CartService {
    /*
    Инжектирует зависимости (репозитории) в сервис.
     */
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository,
                       OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Добавляет продукт в корзину пользователя.
     *
     * @param order Заказ для добавления в корзину.
     * @return Строка с результатом операции (например, "SUCCESS").
     * @throws IllegalStateException если пользователь не аутентифицирован.
     */

    public String addToCart(OrderDTO order) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {

            Optional<ShoppingCart> cartFromRepo = cartRepository.findByUserId(((UserDetails) principal).getUsername());
            ShoppingCart cart;
            if (!cartFromRepo.isPresent()) {
                cart = new ShoppingCart();
                cart.setUserId(((UserDetails) principal).getUsername());
                cartRepository.save(cart);
            } else {
                cart = cartFromRepo.get();
            }

            OrderItem item = new OrderItem();
            Product pt = productRepository.findProductById(order.getProductId())
                    .orElseThrow(() -> new IllegalStateException("Product not found"));
            item.fromDto(pt, cart, order.getQuantity());

            orderRepository.save(item);
            return "SUCCESS";
        } else {
            throw new IllegalStateException("user not authenticated");
        }
    }

    /**
     * Преобразует отформатированную строку цены в целое число.
     *
     * @param price Строка цены в формате "Rs.2,244".
     * @return Целое число представляющее цену.
     */

    private Integer formatPrice(String price) {
        // price with Rs.2,244 --> 2244
        price = price.substring(3).replace(",", "");
        return Integer.parseInt(price);
    }


    /**
     * Возвращает список продуктов в корзине пользователя и общую стоимость.
     *
     * @return HashMap с ключами "products" (список продуктов) и "total" (общая стоимость).
     * @throws IllegalStateException если пользователь не аутентифицирован.
     */

    public HashMap<String, Object> getProducts() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HashMap<String, Object> hs = new HashMap<>();
        hs.put("products", new ArrayList<>());
        hs.put("total", BigDecimal.ZERO);
        if (principal instanceof UserDetails) {
            Optional<ShoppingCart> cartFromRepo = cartRepository.findByUserId(((UserDetails) principal).getUsername());
            ShoppingCart cart;
            if (!cartFromRepo.isPresent()) {
                return hs;
            }
            cart = cartFromRepo.get();
            ArrayList<OrderItem> orders = new ArrayList<>(orderRepository.findByCart_Id(cart.getId()));
            hs.put("products", orders);
            int price = 0;
            for (OrderItem order : orders) {
                if (order.getProduct().getE_arrival().toLowerCase(Locale.ROOT).equals("old")) {
                    price += order.getQuantity() * order.getProduct().getF_discount();
                } else {
                    price += order.getQuantity() * formatPrice((order.getProduct().getD_price()));
                }
            }
            hs.put("total", price);
            return hs;
        } else {
            throw new IllegalStateException("user not authenticated");
        }
    }

    /**
     * Обновляет количество продуктов в заказе в корзине покупок.
     *
     * @param updateOrderDTO Объект, содержащий идентификатор заказа и новое количество.
     * @return Строка с результатом операции (например, "SUCCESS").
     */

    public String updateOrder(UpdateOrderDTO updateOrderDTO) {
        OrderItem item = orderRepository.findById(updateOrderDTO.getId())
                .orElseThrow(() -> new IllegalStateException("order does not exist"));

        item.setQuantity(updateOrderDTO.getQuantity());
        orderRepository.save(item);
        return "SUCCESS";
    }

    /**
     * Удаляет заказ из корзины покупок.
     *
     * @param id Идентификатор заказа для удаления.
     * @return Строка с результатом операции (например, "DELETED").
     */

    public String deleteOrder(String id) {
        OrderItem item = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("order does not exist"));
        orderRepository.delete(item);
        return "DELETED";
    }
}
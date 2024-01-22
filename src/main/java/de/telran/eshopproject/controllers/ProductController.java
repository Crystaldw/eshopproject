package de.telran.eshopproject.controllers;

import de.telran.eshopproject.model.Product;
import de.telran.eshopproject.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Контроллер для обработки запросов связанных с продуктами.
 */

@RestController
@RequestMapping(path = "api/v1/")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Возвращает продукты по типу платья на основе параметра запроса.
     *
     * @param dress Тип платья для поиска продуктов.
     * @return HashMap с продуктами и длиной списка.
     *
     * HTTP-метод: GET
     * Endpoint: /api/v1/dresses
     * Назначение:
     * Возвращает продукты по типу платья на основе параметра запроса.
     * Принимает параметр dress из строки запроса.
     * Возвращает HashMap с продуктами и длиной списка.
     */

    @GetMapping(path = "dresses")
    public HashMap<String, Object> getProductsByDressTypeOnSearch(@RequestParam(name = "dress") String dress){
        HashMap<String, Object> hs = new HashMap<>();
        List<Product> products = productService.getProductsByDressType(dress);
        hs.put("products", products);
        hs.put("length" , products.size());
        return hs;
    }


    /**
     * Возвращает продукты на основе пола и сессии.
     *
     * @param sex   Пол для фильтрации продуктов.
     * @param items Количество продуктов на странице.
     * @param page  Текущая страница.
     * @param request Объект HttpServletRequest для работы с сессией.
     * @return HashMap с продуктами, текущей страницей и общим количеством страниц.
     *
     * HTTP-метод: GET
     * Endpoint: /api/v1/clothes
     * Назначение:
     * Возвращает продукты на основе пола и сессии.
     * Принимает параметры sex, items и page из строки запроса.
     * Возвращает HashMap с продуктами, текущей страницей и общим количеством страниц.
     */

    @GetMapping( path = "clothes")
    public HashMap<String, Object> getProductsBySexAndSession(
            @RequestParam(name = "sex") String sex,
            @RequestParam(defaultValue = "10") Integer items,
            @RequestParam(defaultValue = "0") Integer page,
            HttpServletRequest request){

        List<String> messages = (List<String>) request.getSession().getAttribute("SESSION_STORE");

        if ( messages == null){
            messages = new ArrayList<>();
        }

        Map<Boolean, Long> countBySex = messages.stream().collect(
                Collectors.partitioningBy(
                        (String msg) -> (msg.equals("O")), Collectors.counting() ));

        return productService.getProductsBySex(sex,  items, page, countBySex);

    }
}

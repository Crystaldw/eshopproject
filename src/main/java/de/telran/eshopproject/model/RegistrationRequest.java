package de.telran.eshopproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Запрос на регистрацию пользователя в системе.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationRequest {

    //Имя пользователя.
    private String firstName;

    //Фамилия пользователя.
    private String lastName;

    //Имя пользователя (логин).
    private String userName;

    //Пароль пользователя.
    private String password;

    //Электронная почта пользователя.
    private String email;

    //Пол пользователя.
    private String gender;

}

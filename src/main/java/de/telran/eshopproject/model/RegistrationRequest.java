package de.telran.eshopproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String gender;

}

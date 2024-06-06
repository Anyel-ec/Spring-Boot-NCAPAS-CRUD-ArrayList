package top.anyel.ncapas.service.validation;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidatorService {

    public boolean isValidPassword(String password) {
        // Longitud mínima de 8 caracteres
        if (password == null || password.length() < 8) {
            return false;
        }

        // Al menos una letra mayúscula
        boolean hasUppercase = !password.equals(password.toLowerCase());

        // Al menos una letra minúscula
        boolean hasLowercase = !password.equals(password.toUpperCase());

        // Al menos un dígito
        boolean hasDigit = password.matches(".*\\d.*");

        // Al menos un carácter especial
        boolean hasSpecialChar = !password.matches("[A-Za-z0-9 ]*");

        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }
}
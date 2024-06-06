package top.anyel.ncapas.service.validation;

import org.springframework.stereotype.Service;

@Service
public class CedulaValidatorService {
    public boolean isValidCedula(String cedula) {
        if (cedula == null || cedula.length() != 10) {
            return false;
        }

        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int total = 0;

        try {
            for (int i = 0; i < cedula.length() - 1; i++) {
                int digito = Character.getNumericValue(cedula.charAt(i));
                int producto = digito * coeficientes[i];
                if (producto >= 10) {
                    producto -= 9;
                }
                total += producto;
            }

            int digitoVerificador = Character.getNumericValue(cedula.charAt(9));
            int decenaSuperior = ((total + 9) / 10) * 10;
            int resultado = decenaSuperior - total;

            return resultado == digitoVerificador;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

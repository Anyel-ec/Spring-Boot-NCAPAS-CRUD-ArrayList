package top.anyel.ncapas.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record Person(
        @NotBlank(message = "El campo identificacion no puede estar vacío")
        String identification,
        @NotBlank(message = "El campo firstName no puede estar vacío")
        String firstName,
        @NotBlank(message = "El campo firstName no puede estar vacío")
        String lastName,
        @NotBlank(message = "El campo firstName no puede estar vacío")
        String email,
        @NotNull(message = "La lista de direcciones no puede ser nula")
        @Size(min = 1, message = "Debe haber al menos una dirección")
        List<Address> address

) {

}

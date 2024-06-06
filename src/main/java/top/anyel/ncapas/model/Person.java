package top.anyel.ncapas.model;

import top.anyel.ncapas.shared.utils.exceptions.Guards;

import java.util.List;

public record Person(
        String identification,
        String firstName,
        String lastName,
        String email,
        List<Address> address){
        public Person {
                Guards.notNull(identification, "La identificación no puede ser nula");
                Guards.notNull(firstName, "El nombre no puede ser nulo");
                Guards.notNull(lastName, "El apellido no puede ser nulo");
                Guards.notBlank(identification, "La identificación no puede estar vacía");
                Guards.size(identification, 10, "La identificación debe tener 10 caracteres");
        }

}


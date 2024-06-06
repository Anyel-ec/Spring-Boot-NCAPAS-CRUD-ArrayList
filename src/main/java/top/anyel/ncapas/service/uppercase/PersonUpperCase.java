package top.anyel.ncapas.service.uppercase;

import top.anyel.ncapas.model.Person;
import top.anyel.ncapas.shared.utils.uppercase.UpperCaseImpl;

import static top.anyel.ncapas.service.uppercase.AddressesUpperCase.toUpperCaseAddresses;

public class PersonUpperCase {

    private static final UpperCaseImpl helper = new UpperCaseImpl();


    // convertir en mayúsculas para
    public static Person toUpperCasePerson(Person person) {
            return new Person(
                    helper.upperCase(person.identification()),
                    helper.upperCase(person.firstName()),
                    helper.upperCase(person.lastName()),
                    helper.upperCase(person.email()),
                    toUpperCaseAddresses(person.address())
            );
    }
}

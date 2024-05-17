package top.anyel.ncapas.service;

import top.anyel.ncapas.model.Person;
import top.anyel.ncapas.utils.UpperCaseImpl;

public class PersonUpperCaseService {

    private static UpperCaseImpl helper = new UpperCaseImpl();

    public static Person toUpperCase(Person person) {
        String upperIdentification = helper.upperCase(person.identification());
        String upperFirstName = helper.upperCase(person.fisrtName());
        String upperLastName = helper.upperCase(person.lastName());
        String upperEmails = helper.upperCase(person.email());
        return new Person(upperIdentification, upperFirstName, upperLastName, upperEmails);
    }

}

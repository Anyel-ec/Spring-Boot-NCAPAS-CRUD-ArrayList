package top.anyel.ncapas.repository;

import org.springframework.stereotype.Repository;
import top.anyel.ncapas.model.Person;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    List<Person> persons = new ArrayList<>(); // create and initialize a list of persons

    public Person save (Person person){
        persons.add(person);
        return person;
    }

    public List<Person> findAll(){
        return persons;
    }
}

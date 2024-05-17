package top.anyel.ncapas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.anyel.ncapas.model.Person;
import top.anyel.ncapas.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    @Autowired //in Spring is used to inject a bean in another bean, in this case, PersonRepository is a bean
    private PersonRepository personRepository; // access to methos in PersonRepository

    public Person save(Person person){
        return personRepository.save(person);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

}

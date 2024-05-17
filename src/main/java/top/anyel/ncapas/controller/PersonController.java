package top.anyel.ncapas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.anyel.ncapas.model.Person;
import top.anyel.ncapas.service.PersonService;
import top.anyel.ncapas.service.PersonUpperCaseService;

import java.util.List;

@RestController
@RequestMapping("/persons/v1")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public String index(){
        return "In /persons/v1/swagger-ui.html you can see the documentation of the API";
    }

    @PostMapping("/save")
    public Person save(@RequestBody Person person) {
        return personService.save(PersonUpperCaseService.toUpperCase(person));
    }

    @GetMapping("/findAll")
    public List<Person> findAll(){
        return personService.findAll();
    }

    @GetMapping("/findById/{identification}")
    public Person findById(@PathVariable String identification){
        return personService.findById(identification);
    }

    @PutMapping("/updateById/{identification}")
    public Person updateById(@PathVariable String identification, @RequestBody Person person){
        return personService.updateById(identification, PersonUpperCaseService.toUpperCase(person));
    }

    @DeleteMapping("/deleteById/{identification}")
    public String deleteById(@PathVariable String identification){
        return personService.deleteById(identification);
    }


}

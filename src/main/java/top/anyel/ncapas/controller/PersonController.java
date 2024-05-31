package top.anyel.ncapas.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import top.anyel.ncapas.model.Address;
import top.anyel.ncapas.model.Person;
import top.anyel.ncapas.service.PersonService;
import top.anyel.ncapas.service.uppercase.PersonUpperCaseService;
import top.anyel.ncapas.utils.logger.CustomLoggerFactoryService;
import top.anyel.ncapas.utils.logger.ICustomLoggerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/persons/v1")
public class PersonController {
    private final ICustomLoggerService logger;

    @Autowired
    private PersonService personService;

    @Autowired
    public PersonController(CustomLoggerFactoryService loggerFactoryService) {
        this.logger = loggerFactoryService.getLogger(PersonController.class);
    }

    @GetMapping("/")
    public String index(){
        try {
            /*
            logger.logDebug("Bienvenido a la API de personas");
            */
            return "Bienvenido a la API de personas";
        }
        catch (Exception e){
            logger.logError("Error en el inicio de la aplicacion");
            return "Error en el inicio de la aplicacion";
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Person person) {
        try{
            logger.logInfo("Guardando persona");
            return ResponseEntity.ok(personService.save(PersonUpperCaseService.toUpperCase(person)));
        }
        catch (Exception e){
            logger.logError("Error al guardar persona");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // todo: ADD VALIDATION AND EXCEPTION HANDLING

    @GetMapping("/findAll")
    public List<Person> findAll(){
        try{

        return personService.findAll();
        }
        catch (Exception e){
            logger.logWarn("Error al buscar personas");
            return null;
        }
    }

    @GetMapping("/findById/{identification}")
    public Person findById(@PathVariable String identification){
        try{
        return personService.findById(identification);
        }
        catch (Exception e){
            logger.logError("Error al buscar persona");
            return null;
        }
    }

    @PutMapping("/updateById/{identification}")
    public Person updateById(@PathVariable String identification, @RequestBody Person person){
        try{
            return personService.updateById(identification, PersonUpperCaseService.toUpperCase(person));
        }
        catch (Exception e){
            logger.logError("Error al actualizar persona");
            return null;
        }

    }

    @DeleteMapping("/deleteById/{identification}")
    public String deleteById(@PathVariable String identification){
        return personService.deleteById(identification);
    }


    @GetMapping("/address/house/{identification}")
    public List<Address> findHouseAddressesById(@PathVariable String identification) {
        try {
            return personService.findHouseAddressesById(identification);
        } catch (Exception e) {
            logger.logError("Error al buscar direcciones de casa");
            return null;
        }
    }

}

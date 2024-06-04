package top.anyel.ncapas.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import top.anyel.ncapas.model.Address;
import top.anyel.ncapas.model.Person;
import top.anyel.ncapas.service.PersonService;
import top.anyel.ncapas.service.uppercase.PersonUpperCaseService;
import top.anyel.ncapas.shared.utils.exceptions.ApiError;
import top.anyel.ncapas.shared.utils.exceptions.ApiErrorResponse;
import top.anyel.ncapas.shared.utils.exceptions.Failure;
import top.anyel.ncapas.shared.utils.exceptions.FieldViolation;
import top.anyel.ncapas.shared.utils.logger.CustomLoggerFactoryService;
import top.anyel.ncapas.shared.utils.logger.ICustomLoggerService;

import java.util.List;
import java.util.stream.Collectors;

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
            logger.logError("Error en el inicio de la aplicacion", e);
            return "Error en el inicio de la aplicacion";
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Person person) {
        try{
            logger.logInfo("Guardando persona");
            Person savedPerson = personService.save(PersonUpperCaseService.toUpperCasePerson(person));
            return ResponseEntity.ok(savedPerson);
        }
        catch (Exception e){
            logger.logError("Error al guardar persona", e);
            throw new RuntimeException("Error al guardar persona", e);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Person>> findAll() {
        try {
            List<Person> persons = personService.findAll();
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            logger.logWarn("Error al buscar personas", e);
            throw new RuntimeException("Error al buscar personas", e);
        }
    }

    @GetMapping("/findById/{identification}")
    public ResponseEntity<Person> findById(@PathVariable String identification) {
        try {
            Person person = personService.findById(identification);
            if (person == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            logger.logError("Error al buscar persona", e);
            throw new RuntimeException("Error al buscar persona", e);
        }
    }

    @PutMapping("/updateById/{identification}")
    public ResponseEntity<?> updateById(@PathVariable String identification, @RequestBody Person person) {
        try {
            Person updatedPerson = personService.updateById(identification, PersonUpperCaseService.toUpperCasePerson(person));
            if (updatedPerson == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(updatedPerson);
        } catch (Exception e) {
            logger.logError("Error al actualizar persona", e);
            throw new RuntimeException("Error al actualizar persona", e);
        }
    }

    @DeleteMapping("/deleteById/{identification}")
    public ResponseEntity<String> deleteById(@PathVariable String identification) {
        try {
            String result = personService.deleteById(identification);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.logError("Error al eliminar persona", e);
            throw new RuntimeException("Error al eliminar persona", e);
        }
    }

    @GetMapping("/address/house/{identification}")
    public ResponseEntity<List<Address>> findHouseAddressesById(@PathVariable String identification) {
        try {
            List<Address> addresses = personService.findHouseAddressesById(identification);
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            logger.logError("Error al buscar direcciones de casa", e);
            throw new RuntimeException("Error al buscar direcciones de casa", e);
        }
    }



}

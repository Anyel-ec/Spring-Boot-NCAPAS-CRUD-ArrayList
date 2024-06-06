package top.anyel.ncapas.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.anyel.ncapas.model.Address;
import top.anyel.ncapas.model.Person;
import top.anyel.ncapas.service.PersonService;
import top.anyel.ncapas.service.uppercase.PersonUpperCase;
import top.anyel.ncapas.service.validation.CedulaValidatorService;
import top.anyel.ncapas.service.validation.PasswordValidatorService;
import top.anyel.ncapas.shared.utils.logger.CustomLoggerFactoryService;
import top.anyel.ncapas.shared.utils.logger.ICustomLoggerService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/persons/v1")
public class PersonController {
    private final ICustomLoggerService logger;

    private final PersonService personService;

    private final CedulaValidatorService cedulaValidatorService;

    private final PasswordValidatorService passwordValidatorService;

    @Autowired
    public PersonController(CustomLoggerFactoryService loggerFactoryService, CedulaValidatorService cedulaValidatorService, PersonService personService, PasswordValidatorService passwordValidatorService) {
        this.logger = loggerFactoryService.getLogger(PersonController.class);
        this.cedulaValidatorService = cedulaValidatorService;
        this.personService = personService;
        this.passwordValidatorService = passwordValidatorService;
    }


    @GetMapping("/")
    public ResponseEntity<Void> index() {
        try {
            logger.logInfo("Redirigiendo a Swagger UI");
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/swagger-ui.html")).build();
        } catch (Exception e) {
            logger.logError("Error en el inicio de la aplicacion", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }





    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Person person) {
        try{
            logger.logInfo("Guardando persona");
            Person savedPerson = personService.save(PersonUpperCase.toUpperCasePerson(person));
            return ResponseEntity.ok(savedPerson);
        }
        catch (Exception e){
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
            Person updatedPerson = personService.updateById(identification, PersonUpperCase.toUpperCasePerson(person));
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


    /*opcion 1 */
    @GetMapping("/address/house/{identification}")
    public ResponseEntity<List<Address>> findHouseAddressesById(@PathVariable String identification) {
        List<Address> addresses = personService.findHouseAddressesById(identification);
        if (addresses == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(addresses);
    }

    /*opcion 2
    @GetMapping("/address/house/{identification}")
    public ResponseEntity<List<Address>> findHouseAddressesById2(@PathVariable String identification) {
       return ResponseEntity.ok(personService.findHouseAddressesById(identification));
    }*/

    @GetMapping("/findAllByEmailCity")
    public List<Person> findAllByEmailCity(@RequestParam String email, @RequestParam String city) {
        return personService.findAllByEmailCity(email, city);
    }


    @GetMapping("/validate-cedula")
    public String validateCedula(@RequestParam String cedula) {
        boolean isValid = cedulaValidatorService.isValidCedula(cedula);
        return isValid ? "Cédula válida" : "Cédula inválida";
    }

    @GetMapping("/validate-password")
    public String validatePassword(@RequestParam String password) {
        boolean isValid = passwordValidatorService.isValidPassword(password);
        return isValid ? "Contraseña válida" : "Contraseña inválida";
    }



}

package top.anyel.ncapas.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import top.anyel.ncapas.model.Car;
import top.anyel.ncapas.shared.utils.exceptions.ApiError;
import top.anyel.ncapas.shared.utils.exceptions.ApiErrorResponse;
import top.anyel.ncapas.shared.utils.exceptions.Failure;
import top.anyel.ncapas.shared.utils.exceptions.FieldViolation;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@Tag(name = "Endpoint of Cars", description = "API para la gestión de carros")
@RequestMapping("/cars/v1")
public class CarController {
    @Operation(summary = "Guardar Carro", description = "Es de tipo JSO")
    @PostMapping("/save")
    public ResponseEntity<?> saveCar(@Valid @RequestBody Car car) {
        return ResponseEntity.ok(car);
    }

    @PostMapping("/params")
    public ResponseEntity<?> saveCarParams(@RequestParam String brand, @RequestParam String model) {
        return ResponseEntity.ok(new Car(brand, model));
    }

    @PostMapping("/path/{name}")
    public ResponseEntity<?> saveCarPath(@PathVariable String name) {
        return ResponseEntity.ok(new Car(name, "model"));
    }


}
package top.anyel.ncapas.controller;
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
@RequestMapping("/cars/v1")
public class CarController {

    @PostMapping("/save")
    public ResponseEntity<?> saveCar(@Valid @RequestBody Car car) {
        return ResponseEntity.ok(car);
    }

}
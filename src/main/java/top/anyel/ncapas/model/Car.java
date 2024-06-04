package top.anyel.ncapas.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import top.anyel.ncapas.shared.utils.exceptions.SelfValidating;

@EqualsAndHashCode(callSuper = true)
@Data
public class Car extends SelfValidating<Car> {
    @NotBlank(message = "Brand cannot be blank")
    private String brand;
    @NotBlank(message = "Model cannot be blank")
    private String model;

    @Builder
    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.validateSelf();
    }
}

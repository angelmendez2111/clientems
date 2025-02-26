package edu.unitru.clientems.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ClientRequest {

    @Length(min = 5, max = 10)
    @NotBlank(message = "Ingrese el nombre del cliente")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre no debe contener números ni caracteres especiales")
    private String nombre;

    @Length(min = 5, max = 10)
    @NotBlank(message = "Ingrese el apellido del cliente")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El apellido no debe contener números ni caracteres especiales")
    private String apellido;

    @Length(min = 5, max = 50)
    private String direccion;

    @Positive(message = "La edad debe ser un número positivo")
    @Min(value = 18, message = "La edad debe ser mayor o igual que 18")
    @Max(value = 80, message = "La edad no puede ser mayor que 80")
    private int edad;
}

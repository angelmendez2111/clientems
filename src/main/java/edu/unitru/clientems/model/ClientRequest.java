package edu.unitru.clientems.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ClientRequest {

    private String nombre;
    private String apellido;
    private String direccion;
    @Positive
    private int edad;
}

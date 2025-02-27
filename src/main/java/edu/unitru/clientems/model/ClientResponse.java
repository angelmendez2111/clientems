package edu.unitru.clientems.model;

import lombok.Data;

@Data
public class ClientResponse {
    private int clientId;
    private String nombre;
    private String apellido;
    private Integer amount;
}

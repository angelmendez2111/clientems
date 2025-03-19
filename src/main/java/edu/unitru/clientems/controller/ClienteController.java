package edu.unitru.clientems.controller;

import edu.unitru.clientems.model.ClientRequest;
import edu.unitru.clientems.model.ClientResponse;
import edu.unitru.clientems.repository.entity.Cliente;
import edu.unitru.clientems.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Listar todos los clientes", description = "Obtiene una lista de todos los clientes registrados")
    public List<ClientResponse> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Busca un cliente por su ID")
    public ResponseEntity<ClientResponse> getClientById(
            @Parameter(description = "ID del cliente a buscar") @PathVariable int id) {
        return new ResponseEntity<>(clienteService.getClientById(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente", description = "Registra un nuevo cliente en la base de datos")
    public ResponseEntity<Cliente> crearCliente(
            @Valid @RequestBody ClientRequest clientRequest) {
        return new ResponseEntity<>(clienteService.crearClienteDesdeRequest(clientRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente", description = "Actualiza los datos de un cliente existente")
    public ResponseEntity<ClientResponse> actualizarCliente(
            @Parameter(description = "ID del cliente a actualizar") @PathVariable int id,
            @Valid @RequestBody ClientRequest clientRequest) {
        return new ResponseEntity<>(clienteService.actualizarClienteDesdeRequest(id, clientRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente de la base de datos")
    public ResponseEntity<String> eliminarCliente(
            @Parameter(description = "ID del cliente a eliminar") @PathVariable int id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok("Cliente eliminado.");
    }

    @PostMapping("/message")
    @Operation(summary = "Enviar mensaje", description = "Envía un mensaje desde el servicio")
    public ResponseEntity<Void> sendMessage() {
        clienteService.sendMessage();
        return ResponseEntity.ok().build();
    }
}

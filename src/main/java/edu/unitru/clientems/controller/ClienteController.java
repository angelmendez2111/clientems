package edu.unitru.clientems.controller;

import edu.unitru.clientems.model.ClientRequest;
import edu.unitru.clientems.model.ClientResponse;
import edu.unitru.clientems.repository.entity.Cliente;
import edu.unitru.clientems.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClientResponse> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable int id) {
        return new ResponseEntity<>(clienteService.getClientById(id), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody ClientRequest clientRequest) {
        return new ResponseEntity<>(clienteService.crearClienteDesdeRequest(clientRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> actualizarCliente(@PathVariable int id, @Valid @RequestBody ClientRequest clientRequest) {
        return new ResponseEntity<>(clienteService.actualizarClienteDesdeRequest(id, clientRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable int id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok("Cliente eliminado.");
    }
}

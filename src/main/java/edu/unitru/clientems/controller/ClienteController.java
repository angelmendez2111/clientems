package edu.unitru.clientems.controller;

import edu.unitru.clientems.model.Cliente;
import edu.unitru.clientems.service.ClienteService;
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

    // Listar todos los clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientById(@PathVariable int id) {
        return new ResponseEntity<>(clienteService.getClientById(id), HttpStatus.OK) ;
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.crearCliente(cliente), HttpStatus.CREATED) ;
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        cliente.setClientId(id);
        return new ResponseEntity<>(clienteService.actualizarCliente(cliente), HttpStatus.OK);
    }

    // Eliminar un cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable int id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok("Cliente eliminado.");
    }
}

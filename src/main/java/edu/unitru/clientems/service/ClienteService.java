package edu.unitru.clientems.service;

import edu.unitru.clientems.repository.entity.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> listarClientes();
    Cliente getClientById(int id);
    Cliente crearCliente(Cliente cliente);
    Cliente actualizarCliente(Cliente cliente);
    void eliminarCliente(int id);
}

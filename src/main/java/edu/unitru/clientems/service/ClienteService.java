package edu.unitru.clientems.service;

import edu.unitru.clientems.model.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> listarClientes();
    Cliente obtenerClientePorId(int id);
    Cliente crearCliente(Cliente cliente);
    Cliente actualizarCliente(Cliente cliente);
    void eliminarCliente(int id);
}

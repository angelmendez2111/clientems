package edu.unitru.clientems.service;

import edu.unitru.clientems.model.ClientRequest;
import edu.unitru.clientems.model.ClientResponse;
import edu.unitru.clientems.repository.entity.Cliente;
import java.util.List;

public interface ClienteService {
    List<ClientResponse> listarClientes();
    ClientResponse getClientById(int id);
    Cliente crearCliente(Cliente cliente);
    Cliente actualizarCliente(Cliente cliente);
    void eliminarCliente(int id);
    Cliente crearClienteDesdeRequest(ClientRequest clientRequest);
    ClientResponse actualizarClienteDesdeRequest(int id, ClientRequest clientRequest);

}

package edu.unitru.clientems.service;

import edu.unitru.clientems.exception.NotFoundException;
import edu.unitru.clientems.model.ClientRequest;
import edu.unitru.clientems.model.ClientResponse;
import edu.unitru.clientems.repository.entity.Cliente;
import edu.unitru.clientems.repository.ClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClientResponse> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponse getClientById(int id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        return convertirAResponse(cliente);
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente crearClienteDesdeRequest(ClientRequest clientRequest) {
        Cliente cliente = convertirAEntity(clientRequest);
        return clienteRepository.save(cliente);
    }

    @Override
    public ClientResponse actualizarClienteDesdeRequest(int id, ClientRequest clientRequest) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        clienteExistente.setNombre(clientRequest.getNombre());
        clienteExistente.setApellido(clientRequest.getApellido());
        clienteExistente.setDireccion(clientRequest.getDireccion());
        clienteExistente.setEdad(clientRequest.getEdad());

        Cliente clienteActualizado = clienteRepository.save(clienteExistente);
        return convertirAResponse(clienteActualizado);
    }

    @Override
    public void eliminarCliente(int id) {
        if (clienteRepository.findById(id).isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new NotFoundException("Cliente no encontrado");
        }
    }

    private Cliente convertirAEntity(ClientRequest clientRequest) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clientRequest.getNombre());
        cliente.setApellido(clientRequest.getApellido());
        cliente.setDireccion(clientRequest.getDireccion());
        cliente.setEdad(clientRequest.getEdad());
        return cliente;
    }

    private ClientResponse convertirAResponse(Cliente cliente) {
        ClientResponse response = new ClientResponse();
        response.setClientId(cliente.getClientId());
        response.setNombre(cliente.getNombre());
        response.setApellido(cliente.getApellido());
        return response;
    }
}

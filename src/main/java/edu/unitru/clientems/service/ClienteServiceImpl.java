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

@Service
@Log4j2
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public ClientResponse getClientById(int id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        // Convertimos de Cliente (Entidad) a ClientResponse (DTO)
        ClientResponse response = new ClientResponse();
        response.setClientId(cliente.getClientId());
        response.setNombre(cliente.getNombre());
        response.setApellido(cliente.getApellido());

        return response;
    }


    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        log.info("Client id: "+ cliente.getClientId());
        if(clienteRepository.findById(cliente.getClientId()).isPresent()){
            return clienteRepository.save(cliente);
        }
        throw new NotFoundException("Cliente no encontrado");
    }

    @Override
    public void eliminarCliente(int id) {
        if(clienteRepository.findById(id).isPresent()){
            clienteRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Cliente no encontrado");
        }

    }

    @Override
    public Cliente crearClienteDesdeRequest(ClientRequest clientRequest) {
        Cliente client = new Cliente();
        client.setNombre(clientRequest.getNombre());
        client.setApellido(clientRequest.getApellido());
        client.setDireccion(clientRequest.getDireccion());
        client.setEdad(clientRequest.getEdad());
        return clienteRepository.save(client);
    }

    @Override
    public Cliente actualizarClienteDesdeRequest(int id, ClientRequest clientRequest) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setNombre(clientRequest.getNombre());
            cliente.setApellido(clientRequest.getApellido());
            cliente.setDireccion(clientRequest.getDireccion());
            cliente.setEdad(clientRequest.getEdad());
            return clienteRepository.save(cliente);
        }
        throw new NotFoundException("Cliente no encontrado");
    }

}

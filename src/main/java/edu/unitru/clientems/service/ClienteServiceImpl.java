package edu.unitru.clientems.service;

import edu.unitru.clientems.exception.NotFoundException;
import edu.unitru.clientems.feign.ApiWsppClient;
import edu.unitru.clientems.feign.MessageRequest;
import edu.unitru.clientems.feign.MockyClient;
import edu.unitru.clientems.model.ClientRequest;
import edu.unitru.clientems.model.ClientResponse;
import edu.unitru.clientems.repository.entity.Cliente;
import edu.unitru.clientems.repository.ClienteRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //@Autowired
    //private MockyClient mockyClient;

    @Autowired
    private ApiWsppClient apiWsppClient;

    @Override
    public List<ClientResponse> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente-> this.convertirAResponse(cliente))
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponse getClientById(int id) {
        // Obtener el cliente desde la base de datos
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        /*
        * Integer monto=0;
        // Llamar al servicio externo con Feign
        var response = mockyClient.obtenerMonto();
        if (response.getStatusCode().is2xxSuccessful()) {
            monto = response.getBody().get("amount");
           log.info("Monto obtenido: " + monto);
        } else {
            System.out.println("Error al obtener el monto");
        }*/

        // Retornar el response del cliente
        return convertirAResponse(cliente);
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
    public void sendMessage() {
        clienteRepository.findAll().forEach(client->{
            MessageRequest messageRequest = new MessageRequest();
            messageRequest.setNumber(client.getNumerotelf());
            messageRequest.setMessage("Hola " + client.getNombre() + " " + client.getApellido());
            ResponseEntity<String> response = apiWsppClient.sendMessage(messageRequest);
            if (response.getStatusCode().is2xxSuccessful()){
                log.info("message sent");
            }else {
                log.error("message error");
            }

        });

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
       // response.setAmount(amount);
        return response;
    }


}

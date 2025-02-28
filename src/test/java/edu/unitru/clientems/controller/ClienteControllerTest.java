package edu.unitru.clientems.controller;

import edu.unitru.clientems.model.ClientRequest;
import edu.unitru.clientems.model.ClientResponse;
import edu.unitru.clientems.repository.entity.Cliente;
import edu.unitru.clientems.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    void shouldReturnListClients(){

        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setNombre("piero");
        when(clienteService.listarClientes()).thenReturn(List.of(clientResponse));


        List<ClientResponse> listClients = clienteController.listarClientes();

        assertThat(listClients.getFirst().getNombre()).isEqualTo("piero");
        assertThat(listClients.getFirst().getApellido()).isEqualTo(null);
    }

    @Test
    void shouldReturnResponseClientById(){

        // Arrange
        int clientId = 1;
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setClientId(clientId);
        clientResponse. setNombre("piero");
        clientResponse.setApellido("mendez");
        when(clienteService.getClientById(clientId)).thenReturn(clientResponse);

        //Act
        ResponseEntity <ClientResponse> response = clienteController.getClientById(clientId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getClientId()).isEqualTo(clientId);
        assertThat(response.getBody().getNombre()).isEqualTo("piero");
        assertThat(response.getBody().getApellido()).isEqualTo("mendez");
    }

    @Test
    void shouldCreateClientSuccessfully() {
        // Datos de prueba
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setNombre("Juan");
        clientRequest.setApellido("Perez");
        clientRequest.setEdad(0);

        Cliente clienteCreado = new Cliente();
        clienteCreado.setClientId(1);
        clienteCreado.setNombre("Juan");
        clienteCreado.setApellido("Perez");

        // Configuramos el mock para que cuando se llame a crearClienteDesdeRequest() con el clientRequest,
        // devuelva el objeto clienteCreado
        when(clienteService.crearClienteDesdeRequest(clientRequest)).thenReturn(clienteCreado);

        // Ejecutamos el método a probar
        ResponseEntity<Cliente> response = clienteController.crearCliente(clientRequest);

        // Verificamos que el estado sea 201 Created
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Verificamos que el cuerpo de la respuesta sea el cliente creado
        assertThat(response.getBody().getClientId()).isEqualTo(1);
        assertThat(response.getBody().getNombre()).isEqualTo("Juan");
        assertThat(response.getBody().getApellido()).isEqualTo("Perez");
    }



}
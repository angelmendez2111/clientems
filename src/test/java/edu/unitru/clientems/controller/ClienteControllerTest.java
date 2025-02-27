package edu.unitru.clientems.controller;

import edu.unitru.clientems.model.ClientResponse;
import edu.unitru.clientems.repository.entity.Cliente;
import edu.unitru.clientems.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

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


        List<ClientResponse> responses = clienteController.listarClientes();

        assertThat(responses.getFirst().getNombre()).isEqualTo("piero");
        assertThat(responses.getFirst().getApellido()).isEqualTo(null);
    }

    @Test
    void shouldReturnResponseClientById(){

        ClientResponse clienteTest = new ClientResponse();
        clienteTest.setNombre("Angel");
        //when();

    }

}
package edu.unitru.clientems.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.unitru.clientems.model.ClientRequest;
import edu.unitru.clientems.repository.entity.Cliente;
import edu.unitru.clientems.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
public class ClientControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private ClienteService clienteService;

    @Test
    public void createClientTest() throws Exception
    {

        //Arrange
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setNombre("Juancito");
        clientRequest.setApellido("Perez");
        clientRequest.setEdad(18);
        Cliente clienteCreado = new Cliente();
        clienteCreado.setClientId(1);
        clienteCreado.setNombre("Juancito");
        clienteCreado.setApellido("Perez");
        when(clienteService.crearClienteDesdeRequest(clientRequest)).thenReturn(clienteCreado);


        mvc.perform( MockMvcRequestBuilders
                        .post("/api/v1/clientes")
                        .content(asJsonString(clientRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Juancito"));
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

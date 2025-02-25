package edu.unitru.clientems.service;
import edu.unitru.clientems.exception.NotFoundException;
import edu.unitru.clientems.model.Cliente;
import edu.unitru.clientems.repository.ClienteRepository;
import edu.unitru.clientems.service.ClienteService;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Not;
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
    public Cliente getClientById(int id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(()->new NotFoundException("Cliente no encontrado"));
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
}

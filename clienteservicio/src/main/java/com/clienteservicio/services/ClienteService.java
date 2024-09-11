package com.clienteservicio.services;

import com.clienteservicio.exceptions.ClienteNotFoundException;
import com.clienteservicio.models.Cliente;
import com.clienteservicio.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteProducerService clienteProducerService;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long clienteId) throws ClienteNotFoundException {
        return clienteRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con ID: " + clienteId));
    }

    public Cliente actualizarCliente(Long clienteId, Cliente clienteActualizado) throws ClienteNotFoundException {
        Cliente cliente = obtenerClientePorId(clienteId);
        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setEdad(clienteActualizado.getEdad());
        cliente.setGenero(clienteActualizado.getGenero());
        cliente.setDireccion(clienteActualizado.getDireccion());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setContraseña(clienteActualizado.getContraseña());
        cliente.setEstado(clienteActualizado.isEstado());
        Cliente clienteNew = clienteRepository.save(cliente);
        // Enviar el cliente actualizado al otro microservicio
        clienteProducerService.enviarClienteActualizado(clienteNew);
        return clienteNew;
    }

    public void eliminarCliente(Long clienteId) throws ClienteNotFoundException {
        Cliente cliente = obtenerClientePorId(clienteId);
        clienteRepository.delete(cliente);
    }
}

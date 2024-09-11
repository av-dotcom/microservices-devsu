package com.clienteservicio.tests;


import com.clienteservicio.exceptions.ClienteNotFoundException;
import com.clienteservicio.models.Cliente;
import com.clienteservicio.repositories.ClienteRepository;
import com.clienteservicio.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    public ClienteServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerClientePorIdExistente() throws ClienteNotFoundException {
        Cliente cliente = new Cliente();
        cliente.setClienteId(1L);
        cliente.setNombre("Jose Lema");

        when(clienteRepository.findByClienteId(1L)).thenReturn(Optional.of(cliente));

        Cliente clienteEncontrado = clienteService.obtenerClientePorId(1L);

        assertEquals("Jose Lema", clienteEncontrado.getNombre());
        verify(clienteRepository, times(1)).findByClienteId(1L);
    }

    @Test
    public void testObtenerClientePorIdNoExistente() {
        when(clienteRepository.findByClienteId(1L)).thenReturn(Optional.empty());

        assertThrows(ClienteNotFoundException.class, () -> {
            clienteService.obtenerClientePorId(1L);
        });
    }
}

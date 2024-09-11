package com.clienteservicio.controllers;

import com.clienteservicio.exceptions.ClienteNotFoundException;
import com.clienteservicio.models.Cliente;
import com.clienteservicio.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long clienteId) throws ClienteNotFoundException {
        Cliente cliente = clienteService.obtenerClientePorId(clienteId);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long clienteId, @RequestBody Cliente clienteActualizado) throws ClienteNotFoundException {
        Cliente cliente = clienteService.actualizarCliente(clienteId, clienteActualizado);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long clienteId) throws ClienteNotFoundException {
        clienteService.eliminarCliente(clienteId);
        return ResponseEntity.noContent().build();
    }
}

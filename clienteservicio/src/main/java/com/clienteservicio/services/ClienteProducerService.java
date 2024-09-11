package com.clienteservicio.services;

import com.clienteservicio.dto.ClienteDTO;
import com.clienteservicio.models.Cliente;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarClienteActualizado(Cliente cliente) {
        // Convertir Cliente a ClienteDTO
        ClienteDTO clienteDTO = new ClienteDTO(
                cliente.getClienteId(),
                cliente.getNombre(),
                cliente.getGenero(),
                cliente.getEdad(),
                cliente.getIdentificacion(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getContraseña(),
                cliente.isEstado()
        );

        // Enviar ClienteDTO a la cola
        rabbitTemplate.convertAndSend("clienteExchange", "clienteRoutingKey", clienteDTO);
    }
}

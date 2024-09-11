package com.cuentaservicio.services;

import com.cuentaservicio.dto.ClienteDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ClienteConsumerService {

    @RabbitListener(queues = "clienteQueue")
    public void procesarCliente(ClienteDTO clienteDTO) {
        System.out.println("Cliente recibido: " + clienteDTO.getNombre());
        // Procesar la información del clienteDTO, por ejemplo, crear una cuenta asociada
    }
}

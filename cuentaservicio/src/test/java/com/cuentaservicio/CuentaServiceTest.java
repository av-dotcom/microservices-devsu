package com.microservicio.cuenta.service;


import com.cuentaservicio.exceptions.CuentaNotFoundException;
import com.cuentaservicio.models.Cuenta;
import com.cuentaservicio.respositories.CuentaRepository;
import com.cuentaservicio.services.CuentaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

public class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private CuentaService cuentaService;

    public CuentaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerCuentaPorIdExistente() throws CuentaNotFoundException {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumeroCuenta("123456");
        cuenta.setSaldoInicial(1000.0);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));

        Cuenta cuentaEncontrada = cuentaService.obtenerCuentaPorId(1L);

        assertEquals("123456", cuentaEncontrada.getNumeroCuenta());
        verify(cuentaRepository, times(1)).findById(1L);
    }

    @Test
    public void testObtenerCuentaPorIdNoExistente() {
        when(cuentaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CuentaNotFoundException.class, () -> {
            cuentaService.obtenerCuentaPorId(1L);
        });
    }
}

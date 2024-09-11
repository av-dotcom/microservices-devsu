package com.cuentaservicio.services;

import com.cuentaservicio.exceptions.CuentaNotFoundException;
import com.cuentaservicio.exceptions.MovimientoNotFoundException;
import com.cuentaservicio.exceptions.SaldoInsuficienteException;
import com.cuentaservicio.models.Cuenta;
import com.cuentaservicio.models.Movimiento;
import com.cuentaservicio.respositories.CuentaRepository;
import com.cuentaservicio.respositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public Movimiento registrarMovimiento(Movimiento movimiento) throws CuentaNotFoundException, SaldoInsuficienteException {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con ID: " + movimiento.getCuenta().getId()));

        Double saldoActual = cuenta.getSaldoInicial();

        Double nuevoSaldo = saldoActual + movimiento.getValor();

        if (nuevoSaldo < 0) {
            throw new SaldoInsuficienteException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        movimiento.setFecha(LocalDateTime.now());
        movimiento.setSaldo(nuevoSaldo);

        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> obtenerMovimientosPorCuenta(Long cuentaId) {
        return movimientoRepository.findByCuentaId(cuentaId);
    }

    public Movimiento obtenerMovimientoPorId(Long id) throws MovimientoNotFoundException {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new MovimientoNotFoundException("Movimiento no encontrado con ID: " + id));
    }

    public void eliminarMovimiento(Long id) throws MovimientoNotFoundException {
        Movimiento movimiento = obtenerMovimientoPorId(id);
        movimientoRepository.delete(movimiento);
    }

    public List<Movimiento> obtenerMovimientosPorClienteYFechas(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return movimientoRepository.findByCuentaClienteIdAndFechaBetween(clienteId, fechaInicio, fechaFin);
    }
}

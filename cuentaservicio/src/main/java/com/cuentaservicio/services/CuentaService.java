package com.cuentaservicio.services;

import com.cuentaservicio.exceptions.CuentaNotFoundException;
import com.cuentaservicio.models.Cuenta;
import com.cuentaservicio.respositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta obtenerCuentaPorId(Long id) throws CuentaNotFoundException {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con ID: " + id));
    }

    public Cuenta obtenerCuentaPorNumero(String numeroCuenta) throws CuentaNotFoundException {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con Número: " + numeroCuenta));
    }

    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta actualizarCuenta(Long id, Cuenta cuentaActualizada) throws CuentaNotFoundException {
        Cuenta cuenta = obtenerCuentaPorId(id);
        cuenta.setTipoCuenta(cuentaActualizada.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaActualizada.getSaldoInicial());
        cuenta.setEstado(cuentaActualizada.getEstado());
        return cuentaRepository.save(cuenta);
    }

    public void eliminarCuenta(Long id) throws CuentaNotFoundException {
        Cuenta cuenta = obtenerCuentaPorId(id);
        cuentaRepository.delete(cuenta);
    }
}

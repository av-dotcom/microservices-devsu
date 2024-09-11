package com.cuentaservicio.controllers;

import com.cuentaservicio.exceptions.CuentaNotFoundException;
import com.cuentaservicio.exceptions.MovimientoNotFoundException;
import com.cuentaservicio.exceptions.SaldoInsuficienteException;
import com.cuentaservicio.models.Movimiento;
import com.cuentaservicio.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody Movimiento movimiento) throws CuentaNotFoundException, SaldoInsuficienteException {
        Movimiento nuevoMovimiento = movimientoService.registrarMovimiento(movimiento);
        return ResponseEntity.ok(nuevoMovimiento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtenerMovimientoPorId(@PathVariable Long id) throws MovimientoNotFoundException {
        Movimiento movimiento = movimientoService.obtenerMovimientoPorId(id);
        return ResponseEntity.ok(movimiento);
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorCuenta(@PathVariable Long cuentaId) {
        List<Movimiento> movimientos = movimientoService.obtenerMovimientosPorCuenta(cuentaId);
        return ResponseEntity.ok(movimientos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id) throws MovimientoNotFoundException {
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}

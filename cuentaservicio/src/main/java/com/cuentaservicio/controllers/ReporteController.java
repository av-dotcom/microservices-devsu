package com.cuentaservicio.controllers;

import com.cuentaservicio.models.Cuenta;
import com.cuentaservicio.models.Movimiento;
import com.cuentaservicio.services.CuentaService;
import com.cuentaservicio.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> generarReporte(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {

        List<Movimiento> movimientos = movimientoService.obtenerMovimientosPorClienteYFechas(clienteId, fechaInicio, fechaFin);

        List<Map<String, Object>> reporte = new ArrayList<>();

        for (Movimiento movimiento : movimientos) {
            Map<String, Object> detalle = new HashMap<>();
            Cuenta cuenta = movimiento.getCuenta();

            detalle.put("Fecha", movimiento.getFecha());
            detalle.put("ClienteId", clienteId);
            detalle.put("NumeroCuenta", cuenta.getNumeroCuenta());
            detalle.put("Tipo", cuenta.getTipoCuenta());
            detalle.put("SaldoInicial", cuenta.getSaldoInicial());
            detalle.put("Estado", cuenta.getEstado());
            detalle.put("Movimiento", movimiento.getValor());
            detalle.put("SaldoDisponible", movimiento.getSaldo());

            reporte.add(detalle);
        }

        return ResponseEntity.ok(reporte);
    }
}

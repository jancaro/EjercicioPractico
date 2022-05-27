package com.example.ejercicioPractico.rest;

import java.util.List;

import com.example.ejercicioPractico.domain.Movimiento;
import com.example.ejercicioPractico.domain.vo.MovimientoVo;
import com.example.ejercicioPractico.domain.vo.ReporteVo;
import com.example.ejercicioPractico.servicio.MovimientoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
public class ApiMovimiento {
    @Autowired
    private MovimientoServicio movimientoServicio;

    @PostMapping("cuenta/{cuentaId}")
    public ResponseEntity<String> saveMovimiento(@RequestBody MovimientoVo movimientoVo, @PathVariable("cuentaId") String cuentaId) {
        return ResponseEntity.ok(this.movimientoServicio.saveMovimiento(movimientoVo, cuentaId));
    }

    @GetMapping("cliente/{clienteId}/{desde}/{hasta}")
    public ResponseEntity<List<ReporteVo>> getMovimientos(@PathVariable("clienteId") String clienteId, @PathVariable("desde") Long desde, @PathVariable("hasta") Long hasta) {
        return ResponseEntity.ok(this.movimientoServicio.getMovimientos(clienteId, desde, hasta));
    }
}

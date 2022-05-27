package com.example.ejercicioPractico.rest;

import java.util.List;

import com.example.ejercicioPractico.domain.Cuenta;
import com.example.ejercicioPractico.domain.vo.CuentaVo;
import com.example.ejercicioPractico.servicio.CuentaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class ApiCuenta {
    @Autowired
    private CuentaServicio cuentaServicio;

    @PostMapping("cliente/{clienteId}")
    public ResponseEntity<String> saveCuenta(@RequestBody CuentaVo cuenta, @PathVariable("clienteId") String clienteId) {
        return ResponseEntity.ok(this.cuentaServicio.saveCuenta(cuenta, clienteId));
    }

    @GetMapping("cliente/{clienteId}")
    public ResponseEntity<List<CuentaVo>> getAllCuentasByClienteId(@PathVariable("clienteId") String clienteId) {
        return ResponseEntity.ok(this.cuentaServicio.getAllCuentasByClienteId(clienteId));
    }

    @GetMapping("{id}")
    public ResponseEntity<CuentaVo> getCuentaById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.cuentaServicio.getCuentaById(id));
    }

    @PutMapping("cliente/{clienteId}/cuenta/{cuentaId}")
    public ResponseEntity<String> updateCuenta(@RequestBody CuentaVo cuenta, @PathVariable("clienteId") String clienteId, @PathVariable("cuentaId") String cuentaId) {
        return ResponseEntity.ok(this.cuentaServicio.updateCuenta(cuenta, clienteId, cuentaId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteCuenta(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.cuentaServicio.deleteCuenta(id));
    }
}

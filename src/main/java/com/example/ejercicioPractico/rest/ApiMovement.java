package com.example.ejercicioPractico.rest;

import java.util.List;

import com.example.ejercicioPractico.domain.vo.MovementVo;
import com.example.ejercicioPractico.domain.vo.ReportVo;
import com.example.ejercicioPractico.services.MovementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
public class ApiMovement {
    @Autowired
    private MovementService movementService;

    @PostMapping("cuenta/{accountId}")
    public ResponseEntity<String> saveMovement(@RequestBody MovementVo movementVo, @PathVariable("accountId") String accountId) {
        return ResponseEntity.ok(this.movementService.saveMovement(movementVo, accountId));
    }

    @GetMapping("cliente/{clientId}/{from}/{to}")
    public ResponseEntity<List<ReportVo>> getMovements(@PathVariable("clientId") String clientId, @PathVariable("from") Long from, @PathVariable("to") Long to) {
        return ResponseEntity.ok(this.movementService.getMovements(clientId, from, to));
    }
}

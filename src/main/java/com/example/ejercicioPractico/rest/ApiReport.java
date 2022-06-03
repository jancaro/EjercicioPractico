package com.example.ejercicioPractico.rest;

import com.example.ejercicioPractico.services.ReportService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/reporte")
public class ApiReport {
    @Autowired
    private ReportService reportService;

    @GetMapping("cliente/{clientId}/{from}/{to}")
    public ResponseEntity<Map<String, Object>> getMovements(@PathVariable("clientId") String clientId, @PathVariable("from") Long from, @PathVariable("to") Long to) throws DocumentException {
        return ResponseEntity.ok(this.reportService.generateReportMovementsPdf(clientId, from, to));
    }
}

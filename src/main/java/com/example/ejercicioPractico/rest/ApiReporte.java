package com.example.ejercicioPractico.rest;

import com.example.ejercicioPractico.servicio.ReporteServicio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/reporte")
public class ApiReporte {
    @Autowired
    private ReporteServicio reporteServicio;

    @GetMapping("cliente/{clienteId}/{desde}/{hasta}")
    public ResponseEntity<String> getMovimientos(@PathVariable("clienteId") String clienteId, @PathVariable("desde") Long desde, @PathVariable("hasta") Long hasta) throws DocumentException, IOException {
        return ResponseEntity.ok(this.reporteServicio.generateReporteMovimientosPdf(clienteId, desde, hasta));
    }
}

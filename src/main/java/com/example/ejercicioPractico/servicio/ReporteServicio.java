package com.example.ejercicioPractico.servicio;

import com.example.ejercicioPractico.domain.vo.ReporteVo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.io.*;
import java.util.stream.Stream;

import com.example.ejercicioPractico.domain.Cliente;
import com.example.ejercicioPractico.repositorio.ClienteRepositorio;

@Service
public class ReporteServicio {
    @Autowired
    private MovimientoServicio movimientoServicio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public String generateReporteMovimientosPdf(String clienteId, Long fechaDesde, Long fechaHasta) throws IOException, DocumentException {
        Cliente cliente = this.clienteRepositorio.findById(clienteId).orElse(null);
        List<ReporteVo> movimientos = this.movimientoServicio.getMovimientos(clienteId, fechaDesde, fechaHasta);

        if (cliente != null && movimientos != null) {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            document.open();
            PdfPTable table = new PdfPTable(8);
            addTableHeader(table);
            movimientos.forEach(movimiento -> addRows(table, movimiento));
            document.add(table);
            document.close();
            ByteArrayInputStream pdfStream = new ByteArrayInputStream(baos.toByteArray());
            byte[] archivo = Base64.getEncoder().encode(pdfStream.readAllBytes());
            return new String(archivo, StandardCharsets.UTF_8);
        } else {
            throw new RuntimeException("No existen movimientos.");
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Fecha", "Cliente", "Numero de Cuenta", "Tipo", "Saldo Inicial", "Estado", "Movimiento", "Saldo Disponible")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, ReporteVo reporteVo) {
        table.addCell(reporteVo.getFechaMovimiento().toString());
        table.addCell(reporteVo.getNombreCliente());
        table.addCell(reporteVo.getNumeroCuenta());
        table.addCell(reporteVo.getTipoMovimiento().name());
        table.addCell(reporteVo.getSaldoInicial().toString());
        table.addCell(reporteVo.getEstadoCuenta().toString());
        table.addCell(reporteVo.getMontoMovimiento().toString());
        table.addCell(reporteVo.getSaldoActual().toString());
    }
}

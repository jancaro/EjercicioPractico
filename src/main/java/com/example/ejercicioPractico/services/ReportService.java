package com.example.ejercicioPractico.services;

import com.example.ejercicioPractico.domain.Client;
import com.example.ejercicioPractico.domain.vo.ReportVo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.Map;
import java.util.stream.Stream;

import com.example.ejercicioPractico.repository.ClientRepository;

@Service
public class ReportService {
    @Autowired
    private MovementService movementService;

    @Autowired
    private ClientRepository clientRepository;

    public Map<String, Object> generateReportMovementsPdf(String clientId, Long dateFrom, Long dateTo) throws DocumentException {
        Client client = this.clientRepository.findById(clientId).orElse(null);
        List<ReportVo> movements = this.movementService.getMovements(clientId, dateFrom, dateTo);

        if (client != null && movements != null) {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            document.open();
            PdfPTable table = new PdfPTable(8);
            addTableHeader(table);
            movements.forEach(movement -> addRows(table, movement));
            document.add(table);
            document.close();
            ByteArrayInputStream pdfStream = new ByteArrayInputStream(baos.toByteArray());
            byte[] archivo = Base64.getEncoder().encode(pdfStream.readAllBytes());
            HashMap<String, Object> map = new HashMap<>();
            map.put("result", new String(archivo, StandardCharsets.UTF_8));
            return map;
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

    private void addRows(PdfPTable table, ReportVo reportVo) {
        table.addCell(reportVo.getMovementDate().toString());
        table.addCell(reportVo.getClientName());
        table.addCell(reportVo.getAccountNumber());
        table.addCell(reportVo.getMovementType().name());
        table.addCell(reportVo.getInitialBalance().toString());
        table.addCell(reportVo.getAccountStatus().toString());
        table.addCell(reportVo.getMovementAmount().toString());
        table.addCell(reportVo.getCurrentBalance().toString());
    }
}

package com.example.ejercicioPractico.rest;

import java.util.List;

import com.example.ejercicioPractico.domain.vo.ClientVo;
import com.example.ejercicioPractico.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ApiClient {

    @Autowired
    private ClientService clientService;

    @PostMapping("")
    public ResponseEntity<String> saveClient(@RequestBody ClientVo clientVo) {
        return ResponseEntity.ok(this.clientService.saveClient(clientVo));
    }

    @GetMapping("")
    public ResponseEntity<List<ClientVo>> getAllClients() {
        return ResponseEntity.ok(this.clientService.getAllClients());
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientVo> getClientByIdentification(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.clientService.getClientById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateClient(@RequestBody ClientVo clientVo, @PathVariable("id") String id) {
        return ResponseEntity.ok(this.clientService.updateClient(clientVo, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteClient(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.clientService.deleteClient(id));
    }
}

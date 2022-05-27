package com.example.ejercicioPractico.rest;

import java.util.List;

import com.example.ejercicioPractico.domain.Cliente;
import com.example.ejercicioPractico.domain.vo.ClienteVo;
import com.example.ejercicioPractico.servicio.ClienteServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ApiCliente {

    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping("")
    public ResponseEntity<String> saveCliente(@RequestBody ClienteVo cliente) {
        return ResponseEntity.ok(this.clienteServicio.saveCliente(cliente));
    }

    @GetMapping("")
    public ResponseEntity<List<ClienteVo>> getAllClientes() {
        return ResponseEntity.ok(this.clienteServicio.getAllClientes());
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteVo> getClienteByIdentificacion(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.clienteServicio.getClienteById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCliente(@RequestBody ClienteVo cliente, @PathVariable("id") String id) {
        return ResponseEntity.ok(this.clienteServicio.updateCliente(cliente, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteCliente(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.clienteServicio.deleteCliente(id));
    }
}

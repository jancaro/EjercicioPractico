package com.example.ejercicioPractico.rest;

import java.util.List;

import com.example.ejercicioPractico.domain.vo.AccountVo;
import com.example.ejercicioPractico.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class ApiAccount {
    @Autowired
    private AccountService accountService;

    @PostMapping("cliente/{clientId}")
    public ResponseEntity<String> saveAccount(@RequestBody AccountVo cuenta, @PathVariable("clientId") String clientId) {
        return ResponseEntity.ok(this.accountService.saveAccount(cuenta, clientId));
    }

    @GetMapping("cliente/{clientId}")
    public ResponseEntity<List<AccountVo>> getAllAccountsByClientId(@PathVariable("clientId") String clientId) {
        return ResponseEntity.ok(this.accountService.getAllAccountsByClientId(clientId));
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountVo> getAccountById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.accountService.getAccountById(id));
    }

    @PutMapping("cliente/{clientId}/accountVo/{accountId}")
    public ResponseEntity<String> updateAccount(@RequestBody AccountVo accountVo, @PathVariable("clientId") String clientId, @PathVariable("accountId") String accountId) {
        return ResponseEntity.ok(this.accountService.updateAccount(accountVo, clientId, accountId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.accountService.deleteAccount(id));
    }
}

package com.example.ejercicioPractico.repository;

import com.example.ejercicioPractico.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
   List<Account> findByClient_Id(String clientId);
   List<Account> findByClient_IdAndStatus(String clientId, boolean status);
}

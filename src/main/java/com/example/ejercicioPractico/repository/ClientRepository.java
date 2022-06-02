package com.example.ejercicioPractico.repository;

import java.util.List;

import com.example.ejercicioPractico.domain.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
    Client findByIdentification(String identification);
    List<Client> findByStatus(boolean status);
}

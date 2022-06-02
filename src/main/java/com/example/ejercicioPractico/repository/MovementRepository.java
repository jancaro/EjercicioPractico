package com.example.ejercicioPractico.repository;

import com.example.ejercicioPractico.domain.Movement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, String> {
    List<Movement> findByAccount_IdAndDateBetweenOrderByDateDesc(String accountId, Date minDate, Date maxDate);
    List<Movement> findByAccount_IdOrderByDateDesc(String accountId);
}

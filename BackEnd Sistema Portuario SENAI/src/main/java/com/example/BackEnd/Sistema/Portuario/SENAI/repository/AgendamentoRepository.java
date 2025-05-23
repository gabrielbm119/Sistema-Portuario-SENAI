package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.AgendamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, Integer> {
    Optional<AgendamentoModel> findByIdAgendamento(int idAgendamento);
    Optional<AgendamentoModel> findAllByIdAgendamento(int idAgendamento);
}
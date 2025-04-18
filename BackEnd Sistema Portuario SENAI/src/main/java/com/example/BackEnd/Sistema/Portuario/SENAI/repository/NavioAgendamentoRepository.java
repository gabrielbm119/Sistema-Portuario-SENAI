package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.NavioAgendamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NavioAgendamentoRepository extends JpaRepository<NavioAgendamentoModel, Integer> {
    Optional<NavioAgendamentoModel> findByIdNavioAgendamento(int idNavioAgendamento);
    Optional<NavioAgendamentoModel> findAllByIdNavioAgendamento(int idNavioAgendamento);
}
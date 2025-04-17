package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.DocaAgendamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocaAgendamentoRepository extends JpaRepository<DocaAgendamentoModel, Integer> {
    Optional<DocaAgendamentoModel> findByIdDocaAgendamento(int idDocaAgendamento);
    Optional<DocaAgendamentoModel> findAllByIdDocaAgendamento(int idDocaAgendamento);
}
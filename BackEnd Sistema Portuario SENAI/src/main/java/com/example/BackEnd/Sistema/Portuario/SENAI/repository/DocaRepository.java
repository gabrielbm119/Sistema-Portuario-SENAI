package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.DocaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocaRepository extends JpaRepository<DocaModel, Integer> {
    Optional<DocaModel> findById(int Id);
    Optional<DocaModel> findAllById(int Id);
}
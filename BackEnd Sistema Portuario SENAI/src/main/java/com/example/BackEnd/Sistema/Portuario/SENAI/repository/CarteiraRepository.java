package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.CarteiraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<CarteiraModel, Integer> {
    Optional<CarteiraModel> findByIdCarteira(int idCarteira);
    Optional<CarteiraModel> findAllByIdCarteira(int idCarteira);
}
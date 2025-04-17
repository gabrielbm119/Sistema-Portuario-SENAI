package com.example.BackEnd.Sistema.Portuario.SENAI.repository;

import com.example.BackEnd.Sistema.Portuario.SENAI.models.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Integer> {
    Optional<AdminModel> findByIdAdmin(int idAdmin);
    Optional<AdminModel> findAllByIdAdmin(int idAdmin);
}
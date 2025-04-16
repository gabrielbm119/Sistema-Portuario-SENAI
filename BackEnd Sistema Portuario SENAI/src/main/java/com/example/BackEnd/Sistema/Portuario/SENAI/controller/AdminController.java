package com.example.BackEnd.Sistema.Portuario.SENAI.controller;

import com.example.BackEnd.Sistema.Portuario.SENAI.dto.AdminRecordDto;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.AdminModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.AdminRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @PostMapping
    public ResponseEntity<AdminModel> saveAdmin(@RequestBody @Valid AdminRecordDto adminRecordDto) {
        var adminModel = new AdminModel();
        BeanUtils.copyProperties(adminRecordDto, adminModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminRepository.save(adminModel));
    }

    @GetMapping
    public ResponseEntity<List<AdminModel>> getAllAdmins() {
        return ResponseEntity.status(HttpStatus.OK).body(adminRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAdmin(@PathVariable(value = "id") int id) {
        Optional<AdminModel> admin = adminRepository.findById(id);
        if (admin.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(admin.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAdmin(@PathVariable(value = "id") int id,
                                              @RequestBody @Valid AdminRecordDto adminRecordDto) {
        Optional<AdminModel> admin = adminRepository.findById(id);
        if (admin.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado");
        }
        var adminModel = admin.get();
        BeanUtils.copyProperties(adminRecordDto, adminModel);
        return ResponseEntity.status(HttpStatus.OK).body(adminRepository.save(adminModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable(value = "id") int id) {
        Optional<AdminModel> admin = adminRepository.findById(id);
        if (admin.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado");
        }
        adminRepository.delete(admin.get());
        return ResponseEntity.status(HttpStatus.OK).body("Administrador deletado com sucesso");
    }
}
package com.example.BackEnd.Sistema.Portuario.SENAI.controller;

import com.example.BackEnd.Sistema.Portuario.SENAI.dto.DocaRecordDto;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.DocaModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.DocaRepository;
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
@RequestMapping("/doca")
public class DocaController {

    @Autowired
    DocaRepository docaRepository;

    @PostMapping
    public ResponseEntity<DocaModel> saveDoca(@RequestBody @Valid DocaRecordDto dto) {
        var docaModel = new DocaModel();
        BeanUtils.copyProperties(dto, docaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(docaRepository.save(docaModel));
    }

    @GetMapping
    public ResponseEntity<List<DocaModel>> getAllDocas() {
        return ResponseEntity.status(HttpStatus.OK).body(docaRepository.findAll());
    }

    @GetMapping("/{idDoca}")
    public ResponseEntity<Object> getOneDoca(@PathVariable(value = "idDoca") int idDoca) {
        Optional<DocaModel> doca = docaRepository.findById(idDoca);
        if (doca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doca não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(doca.get());
    }

    @PutMapping("/{idDoca}")
    public ResponseEntity<Object> updateDoca(@PathVariable(value = "idDoca") int idDoca,
                                             @RequestBody @Valid DocaRecordDto dto) {
        Optional<DocaModel> docaOpt = docaRepository.findById(idDoca);
        if (docaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doca não encontrada");
        }

        var docaModel = docaOpt.get();
        BeanUtils.copyProperties(dto, docaModel);
        return ResponseEntity.status(HttpStatus.OK).body(docaRepository.save(docaModel));
    }

    @DeleteMapping("/{idDoca}")
    public ResponseEntity<Object> deleteDoca(@PathVariable(value = "idDoca") int idDoca) {
        Optional<DocaModel> doca = docaRepository.findById(idDoca);
        if (doca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doca não encontrada");
        }

        docaRepository.delete(doca.get());
        return ResponseEntity.status(HttpStatus.OK).body("Doca deletada com sucesso");
    }
}

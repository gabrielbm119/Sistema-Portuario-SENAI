package com.example.BackEnd.Sistema.Portuario.SENAI.controller;

import com.example.BackEnd.Sistema.Portuario.SENAI.dto.NavioRecordDto;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.ClienteModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.NavioModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.ClienteRepository;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.NavioRepository;
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
@RequestMapping("/navio")
public class NavioController {

    @Autowired
    NavioRepository navioRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Object> saveNavio(@RequestBody @Valid NavioRecordDto dto) {
        Optional<ClienteModel> cliente = clienteRepository.findById(dto.idCliente());
        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        var navioModel = new NavioModel();
        BeanUtils.copyProperties(dto, navioModel);
        navioModel.setClNavio(cliente.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(navioRepository.save(navioModel));
    }

    @GetMapping
    public ResponseEntity<List<NavioModel>> getAllNavios() {
        return ResponseEntity.status(HttpStatus.OK).body(navioRepository.findAll());
    }

    @GetMapping("/{idNavio}")
    public ResponseEntity<Object> getOneNavio(@PathVariable(value = "idNavio") int idNavio) {
        Optional<NavioModel> navio = navioRepository.findById(idNavio);
        if (navio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Navio não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(navio.get());
    }

    @PutMapping("/{idNavio}")
    public ResponseEntity<Object> updateNavio(@PathVariable(value = "idNavio") int idNavio,
                                              @RequestBody @Valid NavioRecordDto dto) {
        Optional<NavioModel> navioOpt = navioRepository.findById(idNavio);
        if (navioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Navio não encontrado");
        }

        Optional<ClienteModel> cliente = clienteRepository.findById(dto.idCliente());
        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        var navioModel = navioOpt.get();
        BeanUtils.copyProperties(dto, navioModel);
        navioModel.setClNavio(cliente.get());

        return ResponseEntity.status(HttpStatus.OK).body(navioRepository.save(navioModel));
    }

    @DeleteMapping("/{idNavio}")
    public ResponseEntity<Object> deleteNavio(@PathVariable(value = "idNavio") int idNavio) {
        Optional<NavioModel> navio = navioRepository.findById(idNavio);
        if (navio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Navio não encontrado");
        }

        navioRepository.delete(navio.get());
        return ResponseEntity.status(HttpStatus.OK).body("Navio deletado com sucesso");
    }
}
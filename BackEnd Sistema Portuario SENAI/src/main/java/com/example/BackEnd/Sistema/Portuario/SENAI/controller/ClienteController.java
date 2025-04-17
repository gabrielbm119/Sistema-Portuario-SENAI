package com.example.BackEnd.Sistema.Portuario.SENAI.controller;

import com.example.BackEnd.Sistema.Portuario.SENAI.dto.ClienteRecordDto;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.ClienteModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.ClienteRepository;
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
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<ClienteModel> saveCliente(@RequestBody @Valid ClienteRecordDto dto) {
        var clienteModel = new ClienteModel();
        BeanUtils.copyProperties(dto, clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(clienteModel));
    }

    @GetMapping
    public ResponseEntity<List<ClienteModel>> getAllClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Object> getOneCliente(@PathVariable(value = "idCliente") int idCliente) {
        Optional<ClienteModel> cliente = clienteRepository.findById(idCliente);
        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Object> updateCliente(@PathVariable(value = "idCliente") int idCliente,
                                                @RequestBody @Valid ClienteRecordDto dto) {
        Optional<ClienteModel> clienteOpt = clienteRepository.findById(idCliente);
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        var clienteModel = clienteOpt.get();
        BeanUtils.copyProperties(dto, clienteModel);
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(clienteModel));
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "idCliente") int idCliente) {
        Optional<ClienteModel> cliente = clienteRepository.findById(idCliente);
        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        clienteRepository.delete(cliente.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso");
    }
}
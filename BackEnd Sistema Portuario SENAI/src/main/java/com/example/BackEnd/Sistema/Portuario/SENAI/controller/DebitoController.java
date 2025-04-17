package com.example.BackEnd.Sistema.Portuario.SENAI.controller;

import com.example.BackEnd.Sistema.Portuario.SENAI.dto.DebitoRecordDto;
import com.example.BackEnd.Sistema.Portuario.SENAI.enums.StatusDebito;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.AgendamentoModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.ClienteModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.DebitoModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.AgendamentoRepository;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.ClienteRepository;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.DebitoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/debito")
public class DebitoController {

    @Autowired
    DebitoRepository debitoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @PostMapping
    public ResponseEntity<Object> saveDebito(@RequestBody @Valid DebitoRecordDto dto) {
        Optional<ClienteModel> clienteOpt = clienteRepository.findById(dto.idCliente());
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        Optional<AgendamentoModel> agendamentoOpt = agendamentoRepository.findById(dto.idAgendamento());
        if (agendamentoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
        }

        var debitoModel = new DebitoModel();
        BeanUtils.copyProperties(dto, debitoModel);
        debitoModel.setClDebito(clienteOpt.get());
        debitoModel.setAgDebito(agendamentoOpt.get());
        debitoModel.setDgDebito(LocalDateTime.now());
        debitoModel.setStDebito(StatusDebito.PENDENTE);

        return ResponseEntity.status(HttpStatus.CREATED).body(debitoRepository.save(debitoModel));
    }

    @GetMapping
    public ResponseEntity<List<DebitoModel>> getAllDebitos() {
        return ResponseEntity.status(HttpStatus.OK).body(debitoRepository.findAll());
    }

    @GetMapping("/{idDebito}")
    public ResponseEntity<Object> getOneDebito(@PathVariable(value = "idDebito") int idDebito) {
        Optional<DebitoModel> debito = debitoRepository.findById(idDebito);
        if (debito.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Débito não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(debito.get());
    }

    @PutMapping("/{idDebito}")
    public ResponseEntity<Object> updateDebito(@PathVariable(value = "idDebito") int idDebito,
                                               @RequestBody @Valid DebitoRecordDto dto) {
        Optional<DebitoModel> debitoOpt = debitoRepository.findById(idDebito);
        if (debitoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Débito não encontrado");
        }

        Optional<ClienteModel> clienteOpt = clienteRepository.findById(dto.idCliente());
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        Optional<AgendamentoModel> agendamentoOpt = agendamentoRepository.findById(dto.idAgendamento());
        if (agendamentoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
        }

        var debitoModel = debitoOpt.get();
        BeanUtils.copyProperties(dto, debitoModel);
        debitoModel.setClDebito(clienteOpt.get());
        debitoModel.setAgDebito(agendamentoOpt.get());

        return ResponseEntity.status(HttpStatus.OK).body(debitoRepository.save(debitoModel));
    }

    @DeleteMapping("/{idDebito}")
    public ResponseEntity<Object> deleteDebito(@PathVariable(value = "idDebito") int idDebito) {
        Optional<DebitoModel> debito = debitoRepository.findById(idDebito);
        if (debito.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Débito não encontrado");
        }

        debitoRepository.delete(debito.get());
        return ResponseEntity.status(HttpStatus.OK).body("Débito deletado com sucesso");
    }
}
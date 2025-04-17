package com.example.BackEnd.Sistema.Portuario.SENAI.controller;

import com.example.BackEnd.Sistema.Portuario.SENAI.dto.AgendamentoRecordDto;
import com.example.BackEnd.Sistema.Portuario.SENAI.enums.StatusAgendamento;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.AgendamentoModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.DocaModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.NavioModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.AgendamentoRepository;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.DocaRepository;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.NavioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    DocaRepository docaRepository;

    @Autowired
    NavioRepository navioRepository;

    @PostMapping
    public ResponseEntity<Object> saveAgendamento(@RequestBody @Valid AgendamentoRecordDto dto) {
        Optional<DocaModel> doca = docaRepository.findById(dto.idDoca());
        Optional<NavioModel> navio = navioRepository.findById(dto.idNavio());

        if (doca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doca não encontrada");
        }

        if (navio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Navio não encontrado");
        }

        AgendamentoModel agendamento = new AgendamentoModel();
        agendamento.setDgAgendamento(LocalDateTime.now());
        agendamento.setDiAgendamento(dto.diAgendamento());
        agendamento.setDfAgendamento(dto.dfAgendamento());
        agendamento.setDcAgendamento(doca.get());
        agendamento.setNvAgendamento(navio.get());
        agendamento.setStAgentamento(StatusAgendamento.PENDENTE); // ou outro valor padrão

        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoRepository.save(agendamento));
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoModel>> getAllAgendamentos() {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoRepository.findAll());
    }

    @GetMapping("/{idAgendamento}")
    public ResponseEntity<Object> getOneAgendamento(@PathVariable(value = "idAgendamento") int idAgendamento) {
        Optional<AgendamentoModel> agendamento = agendamentoRepository.findById(idAgendamento);
        if (agendamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(agendamento.get());
    }

    @PutMapping("/{idAgendamento}")
    public ResponseEntity<Object> updateAgendamento(@PathVariable(value = "idAgendamento") int idAgendamento,
                                                    @RequestBody @Valid AgendamentoRecordDto dto) {
        Optional<AgendamentoModel> agendamentoOpt = agendamentoRepository.findById(idAgendamento);
        if (agendamentoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
        }

        Optional<DocaModel> doca = docaRepository.findById(dto.idDoca());
        Optional<NavioModel> navio = navioRepository.findById(dto.idNavio());

        if (doca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doca não encontrada");
        }

        if (navio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Navio não encontrado");
        }

        var agendamento = agendamentoOpt.get();
        agendamento.setDiAgendamento(dto.diAgendamento());
        agendamento.setDfAgendamento(dto.dfAgendamento());
        agendamento.setDcAgendamento(doca.get());
        agendamento.setNvAgendamento(navio.get());

        return ResponseEntity.status(HttpStatus.OK).body(agendamentoRepository.save(agendamento));
    }

    @DeleteMapping("/{idAgendamento}")
    public ResponseEntity<Object> deleteAgendamento(@PathVariable(value = "idAgendamento") int idAgendamento) {
        Optional<AgendamentoModel> agendamento = agendamentoRepository.findById(idAgendamento);
        if (agendamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
        }

        agendamentoRepository.delete(agendamento.get());
        return ResponseEntity.status(HttpStatus.OK).body("Agendamento deletado com sucesso");
    }
}
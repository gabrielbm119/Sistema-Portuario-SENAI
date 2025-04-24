package com.example.BackEnd.Sistema.Portuario.SENAI.controller;

import com.example.BackEnd.Sistema.Portuario.SENAI.dto.AgendamentoRecordDto;
import com.example.BackEnd.Sistema.Portuario.SENAI.enums.StatusAgendamento;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.*;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @Autowired
    NavioAgendamentoRepository navioAgendamentoRepository;

    @Autowired
    DocaAgendamentoRepository docaAgendamentoRepository;

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
        agendamento.setDgAgendamento(LocalDate.now());
        agendamento.setHgAgendamento(AgendamentoModel.timeToString(LocalTime.now()));
        agendamento.setDiAgendamento(dto.diAgendamento());
        agendamento.setHiAgendamento(dto.hiAgendamento());
        agendamento.setDfAgendamento(dto.dfAgendamento());
        agendamento.setHfAgendamento(dto.hfAgendamento());
        agendamento.setDcAgendamento(doca.get());
        agendamento.setNvAgendamento(navio.get());
        agendamento.setStAgentamento(StatusAgendamento.PENDENTE); // ou outro valor padrão

        // Salva o agendamento
        AgendamentoModel agendamentoSalvo = agendamentoRepository.save(agendamento);

        // Cria relacionamento com Navio -> NavioAgendamento
        NavioAgendamentoModel navioAgendamento = new NavioAgendamentoModel();
        navioAgendamento.setNvNavioAgendamento(navio.get());
        navioAgendamento.setAgNavioAgendamento(agendamentoSalvo);
        navioAgendamentoRepository.save(navioAgendamento);

        // Cria relacionamento com Doca -> DocaAgendamento
        DocaAgendamentoModel docaAgendamento = new DocaAgendamentoModel();
        docaAgendamento.setAgDocaAgendamento(agendamentoSalvo);
        docaAgendamento.setDcDocaAgendamento(doca.get());
        docaAgendamentoRepository.save(docaAgendamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoSalvo);
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
        agendamento.setHiAgendamento(dto.hiAgendamento());
        agendamento.setDfAgendamento(dto.dfAgendamento());
        agendamento.setHfAgendamento(dto.hfAgendamento());
        agendamento.setDcAgendamento(doca.get());
        agendamento.setNvAgendamento(navio.get());
        agendamento.setStAgentamento(dto.stAgendamento());

        AgendamentoModel agendamentoAtualizado = agendamentoRepository.save(agendamento);

        // Verifica se já existe um relacionamento entre esse agendamento e algum navio
        Optional<NavioAgendamentoModel> relacionamentoNavioExistente = navioAgendamentoRepository
                .findByAgNavioAgendamento(agendamento);

        if (relacionamentoNavioExistente.isPresent()) {
            NavioAgendamentoModel navioAgendamento = relacionamentoNavioExistente.get();
            navioAgendamento.setNvNavioAgendamento(navio.get()); // atualiza navio se necessário
            navioAgendamentoRepository.save(navioAgendamento);
        } else {
            // cria novo relacionamento se não existir
            NavioAgendamentoModel novoRelacionamento = new NavioAgendamentoModel();
            novoRelacionamento.setAgNavioAgendamento(agendamentoAtualizado);
            novoRelacionamento.setNvNavioAgendamento(navio.get());
            navioAgendamentoRepository.save(novoRelacionamento);
        }

        // Verifica se já existe um relacionamento entre esse agendamento e alguma doca
        Optional<DocaAgendamentoModel> relacionamentoDocaExistente = docaAgendamentoRepository
                .findByAgDocaAgendamento(agendamento);

        if (relacionamentoDocaExistente.isPresent()) {
            DocaAgendamentoModel docaAgendamento = relacionamentoDocaExistente.get();
            docaAgendamento.setDcDocaAgendamento(doca.get()); // atualiza doca se necessário
            docaAgendamentoRepository.save(docaAgendamento);
        } else {
            // cria novo relacionamento se não existir
            DocaAgendamentoModel novoRelacionamento = new DocaAgendamentoModel();
            novoRelacionamento.setAgDocaAgendamento(agendamentoAtualizado);
            novoRelacionamento.setDcDocaAgendamento(doca.get());
            docaAgendamentoRepository.save(novoRelacionamento);
        }

        return ResponseEntity.status(HttpStatus.OK).body(agendamentoAtualizado);
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
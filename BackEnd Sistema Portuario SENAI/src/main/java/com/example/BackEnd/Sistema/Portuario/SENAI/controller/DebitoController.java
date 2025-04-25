package com.example.BackEnd.Sistema.Portuario.SENAI.controller;

import com.example.BackEnd.Sistema.Portuario.SENAI.dto.DebitoRecordDto;
import com.example.BackEnd.Sistema.Portuario.SENAI.enums.StatusDebito;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.*;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.AgendamentoRepository;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.CarteiraRepository;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.ClienteDebitoRepository;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.DebitoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/debito")
public class DebitoController {

    @Autowired
    DebitoRepository debitoRepository;

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    ClienteDebitoRepository clienteDebitoRepository;

    @Autowired
    CarteiraRepository carteiraRepository;

    @PostMapping
    public ResponseEntity<Object> saveDebito(@RequestBody @Valid DebitoRecordDto dto) {
        Optional<AgendamentoModel> agendamentoOpt = agendamentoRepository.findById(dto.idAgendamento());
        if (agendamentoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
        }

        var debitoModel = new DebitoModel();
        BeanUtils.copyProperties(dto, debitoModel);
        debitoModel.setClDebito(agendamentoOpt.get().getNvAgendamento().getClNavio());
        debitoModel.setAgDebito(agendamentoOpt.get());
        debitoModel.setDgDebito(LocalDate.now());
        debitoModel.setHgDebito(DebitoModel.timeToString(LocalTime.now()));
        debitoModel.setDvDebito(dto.dvDebito());
        debitoModel.setStDebito(StatusDebito.PENDENTE);

        // Salvar Débito
        DebitoModel debitoSalvo = debitoRepository.save(debitoModel);

        // Criar ClienteDebito
        ClienteDebitoModel clienteDebito = new ClienteDebitoModel();
        clienteDebito.setClClienteDebito(debitoSalvo.getClDebito());
        clienteDebito.setDbClienteDebito(debitoSalvo);

        // Salvar relação ClienteDebito
        clienteDebitoRepository.save(clienteDebito);

        return ResponseEntity.status(HttpStatus.CREATED).body(debitoSalvo);
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

    @PutMapping("/pagar/{idDebito}")
    public ResponseEntity<Object> pagarDebito(@PathVariable int idDebito) {
        Optional<DebitoModel> debitoOpt = debitoRepository.findById(idDebito);
        if (debitoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Débito não encontrado");
        }

        DebitoModel debito = debitoOpt.get();

        if (debito.getStDebito() == StatusDebito.PAGO) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Débito já está pago.");
        }

        ClienteModel cliente = debito.getClDebito();
        Optional<CarteiraModel> carteiraOpt = carteiraRepository
                .findAll()
                .stream()
                .filter(c -> c.getClCarteira().getIdCliente().equals(cliente.getIdCliente()))
                .findFirst();

        if (carteiraOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carteira do cliente não encontrada");
        }

        CarteiraModel carteira = carteiraOpt.get();
        if (carteira.getSdCarteira().compareTo(debito.getVlDebito()) < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente na carteira");
        }

        // Deduz o valor e atualiza a carteira
        carteira.setSdCarteira(carteira.getSdCarteira().subtract(debito.getVlDebito()));
        carteiraRepository.save(carteira);

        // Atualiza o débito
        debito.setStDebito(StatusDebito.PAGO);
        debito.setDpDebito(LocalDate.now());
        debito.setHpDebito(DebitoModel.timeToString(LocalTime.now()));
        debitoRepository.save(debito);

        return ResponseEntity.status(HttpStatus.OK).body("Débito pago com sucesso");
    }

    @PutMapping("/{idDebito}")
    public ResponseEntity<Object> updateDebito(@PathVariable(value = "idDebito") int idDebito,
                                               @RequestBody @Valid DebitoRecordDto dto) {
        Optional<DebitoModel> debitoOpt = debitoRepository.findById(idDebito);
        if (debitoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Débito não encontrado");
        }

        Optional<AgendamentoModel> agendamentoOpt = agendamentoRepository.findById(dto.idAgendamento());
        if (agendamentoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
        }

        DebitoModel debitoModel = debitoOpt.get();
        ClienteModel clienteAnterior = debitoModel.getClDebito();

        // Atualiza campos do débito
        BeanUtils.copyProperties(dto, debitoModel);
        ClienteModel clienteNovo = agendamentoOpt.get().getNvAgendamento().getClNavio();
        debitoModel.setClDebito(clienteNovo);
        debitoModel.setAgDebito(agendamentoOpt.get());
        debitoModel.setStDebito(dto.stDebito());

        DebitoModel debitoAtualizado = debitoRepository.save(debitoModel);

        // Verifica se o cliente foi alterado
        if (!clienteAnterior.getIdCliente().equals(clienteNovo.getIdCliente())) {
            // Atualiza a entrada em ClienteDebito
            ClienteDebitoModel clienteDebito = clienteDebitoRepository
                    .findByDbClienteDebito_IdDebito(idDebito)
                    .orElse(null);

            if (clienteDebito != null) {
                clienteDebito.setClClienteDebito(clienteNovo);
                clienteDebitoRepository.save(clienteDebito);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(debitoAtualizado);
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
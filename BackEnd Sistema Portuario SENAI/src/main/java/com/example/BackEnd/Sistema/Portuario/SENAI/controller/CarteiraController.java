package com.example.BackEnd.Sistema.Portuario.SENAI.controller;

import com.example.BackEnd.Sistema.Portuario.SENAI.dto.CarteiraRecordDto;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.CarteiraModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.models.ClienteModel;
import com.example.BackEnd.Sistema.Portuario.SENAI.repository.CarteiraRepository;
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
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Object> saveCarteira(@RequestBody @Valid CarteiraRecordDto dto) {
        Optional<ClienteModel> clienteOpt = clienteRepository.findById(dto.idCliente());
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        var carteiraModel = new CarteiraModel();
        BeanUtils.copyProperties(dto, carteiraModel);
        carteiraModel.setClCarteira(clienteOpt.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(carteiraRepository.save(carteiraModel));
    }

    @GetMapping
    public ResponseEntity<List<CarteiraModel>> getAllCarteiras() {
        return ResponseEntity.status(HttpStatus.OK).body(carteiraRepository.findAll());
    }

    @GetMapping("/{idCarteira}")
    public ResponseEntity<Object> getOneCarteira(@PathVariable(value = "idCarteira") int idCarteira) {
        Optional<CarteiraModel> carteira = carteiraRepository.findById(idCarteira);
        if (carteira.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carteira não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(carteira.get());
    }

    @PutMapping("/{idCarteira}")
    public ResponseEntity<Object> updateCarteira(@PathVariable(value = "idCarteira") int idCarteira,
                                                 @RequestBody @Valid CarteiraRecordDto dto) {
        Optional<CarteiraModel> carteiraOpt = carteiraRepository.findById(idCarteira);
        if (carteiraOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carteira não encontrada");
        }

        Optional<ClienteModel> clienteOpt = clienteRepository.findById(dto.idCliente());
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        var carteiraModel = carteiraOpt.get();
        BeanUtils.copyProperties(dto, carteiraModel);
        carteiraModel.setClCarteira(clienteOpt.get());

        return ResponseEntity.status(HttpStatus.OK).body(carteiraRepository.save(carteiraModel));
    }

    @DeleteMapping("/{idCarteira}")
    public ResponseEntity<Object> deleteCarteira(@PathVariable(value = "idCarteira") int idCarteira) {
        Optional<CarteiraModel> carteira = carteiraRepository.findById(idCarteira);
        if (carteira.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carteira não encontrada");
        }

        carteiraRepository.delete(carteira.get());
        return ResponseEntity.status(HttpStatus.OK).body("Carteira deletada com sucesso");
    }
}
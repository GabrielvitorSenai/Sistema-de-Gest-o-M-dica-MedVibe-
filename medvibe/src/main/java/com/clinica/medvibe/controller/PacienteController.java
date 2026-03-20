package com.clinica.medvibe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.medvibe.dto.PacienteDTO;
import com.clinica.medvibe.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    // Injeção de dependência via construtor
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // CREATE - Endpoint (rota) para criar um cliente retornando um responseEntity
    @PostMapping("/salvar")
    public ResponseEntity<PacienteDTO> criarPacientes(@RequestBody PacienteDTO pacienteDTO) {

        PacienteDTO pacienteCriado = pacienteService.criarPaciente(pacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCriado);
    }

    // RESTORE - Endpoint(rota para buscar todos os clientes)
    @GetMapping("/listar")
    public ResponseEntity<List<PacienteDTO>> listarPacientes() {
        List<PacienteDTO> pacientes = pacienteService.listarPacientes();

        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pacientes);
    }
    // RESTORE - Endpoint(rota para buscar todos os clientes)
    @GetMapping("/listar/{id}")
    public ResponseEntity<List<PacienteDTO>> listarPacientesId() {
        List<PacienteDTO> pacientes = pacienteService.listarPacientesId();

        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pacientes);
    }
}
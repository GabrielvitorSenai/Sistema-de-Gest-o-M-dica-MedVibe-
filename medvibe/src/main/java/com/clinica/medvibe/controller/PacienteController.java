package com.clinica.medvibe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // RESTORE - Endpoint para buscar um cliente por ID
    @GetMapping("listar/{id}")
    public ResponseEntity<PacienteDTO> listarPacientesId(@PathVariable Long id) {

        PacienteDTO paciente = pacienteService.listarPacientesId(id);

        return ResponseEntity.ok(paciente);
    }

    // UPDATE - Endpoint (rota para att um Cliente)
    @PutMapping("put/{id}")
    public ResponseEntity<PacienteDTO> atualizarPacientePut(@PathVariable long id, @RequestBody PacienteDTO pacienteDTO) {

        PacienteDTO pacienteAtualizado = pacienteService.atualizarPacientePut(id, pacienteDTO);

        return ResponseEntity.ok(pacienteAtualizado);
    }

    // UPDATE - Endpoint (rota para att um cliente)Parcial
    @PutMapping("patch/{id}")
    public ResponseEntity<PacienteDTO> atualizarPacientePatch(@PathVariable long id, @RequestBody PacienteDTO pacienteDTO) {

        PacienteDTO pacienteAtualizado = pacienteService.atualizarPacientePatch(id, pacienteDTO);

        return ResponseEntity.ok(pacienteAtualizado);
    }

    // DELETE - Endpoint (rota para excluir um aluno)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {

        pacienteService.excluirPaciente(id);

        return ResponseEntity.noContent().build();
    }

}

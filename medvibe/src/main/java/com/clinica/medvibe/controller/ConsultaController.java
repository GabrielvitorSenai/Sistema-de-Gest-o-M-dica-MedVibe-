package com.clinica.medvibe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.medvibe.dto.ConsultaDTO;
import com.clinica.medvibe.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<ConsultaDTO> criarConsulta(@RequestBody ConsultaDTO consultaDTO) {
        ConsultaDTO criada = consultaService.criarConsulta(consultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ConsultaDTO>> listarConsultas() {
        List<ConsultaDTO> lista = consultaService.listarConsultas();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<ConsultaDTO> listarConsultaId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.listarConsultaId(id));
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<ConsultaDTO> atualizarConsultaPut(@PathVariable Long id, @RequestBody ConsultaDTO dto) {
        return ResponseEntity.ok(consultaService.atualizarConsultaPut(id, dto));
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<ConsultaDTO> atualizarConsultaPatch(@PathVariable Long id, @RequestBody ConsultaDTO dto) {
        return ResponseEntity.ok(consultaService.atualizarConsultaPatch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsulta(@PathVariable Long id) {
        consultaService.excluirConsulta(id);
        return ResponseEntity.noContent().build();
    }
}
package com.clinica.medvibe.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinica.medvibe.dto.MedicoDTO;
import com.clinica.medvibe.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    // CREATE
    @PostMapping("/salvar")
    public ResponseEntity<MedicoDTO> criarMedicos(@RequestBody MedicoDTO medicoDTO) {
        MedicoDTO medicoCriado = medicoService.criarMedico(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoCriado);
    }

    // READ ALL
    @GetMapping("/listar")
    public ResponseEntity<List<MedicoDTO>> listarMedicos() {
        List<MedicoDTO> medicos = medicoService.listarMedicos();
        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicos);
    }

    // READ BY ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<MedicoDTO> listarMedicosId(@PathVariable Long id) {
        MedicoDTO medico = medicoService.listarMedicosId(id);
        return ResponseEntity.ok(medico);
    }

    // UPDATE TOTAL
    @PutMapping("/put/{id}")
    public ResponseEntity<MedicoDTO> atualizarMedicoPut(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        MedicoDTO medicoAtualizado = medicoService.atualizarMedicoPut(id, medicoDTO);
        return ResponseEntity.ok(medicoAtualizado);
    }

    // UPDATE PARCIAL (Ajustado para PatchMapping)
    @PatchMapping("/patch/{id}")
    public ResponseEntity<MedicoDTO> atualizarMedicoPatch(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        MedicoDTO medicoAtualizado = medicoService.atualizarMedicoPatch(id, medicoDTO);
        return ResponseEntity.ok(medicoAtualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {
        medicoService.excluirMedico(id);
        return ResponseEntity.noContent().build();
    }
}
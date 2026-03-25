package com.clinica.medvibe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinica.medvibe.dto.PacienteDTO;
import com.clinica.medvibe.model.Paciente;
import com.clinica.medvibe.repository.PacienteRepository;

import jakarta.persistence.EntityNotFoundException;





@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository){
        this.pacienteRepository = pacienteRepository;
    }

   // Método para criar um novo paciente - CREATE
   public PacienteDTO criarPaciente(PacienteDTO pacienteDTO) {
    Paciente paciente = new Paciente();
    paciente.setNome(pacienteDTO.nome());
    paciente.setIdade(pacienteDTO.idade());
    paciente.setEmail(pacienteDTO.email());
    paciente.setTelefone(pacienteDTO.telefone());

    Paciente pacienteSalvo = pacienteRepository.save(paciente);
    return new PacienteDTO(pacienteSalvo.getId(), pacienteSalvo.getNome(), pacienteSalvo.getIdade(), pacienteSalvo.getEmail(), pacienteSalvo.getTelefone());
}

// Metodo para obter todos os Pacientes - RESTORE(READ)
public List<PacienteDTO> listarPacientes() {
    List<Paciente> pacienteEntity = pacienteRepository.findAll();

    return pacienteEntity.stream().map(paciente -> new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getIdade(),
            paciente.getEmail(), paciente.getTelefone())).toList();

}

// Método para obter UM Paciente por id (READ)
public PacienteDTO listarPacientesId(Long id) {
    // Busca o paciente. Se não encontrar, lança uma exceção.
    Paciente paciente = pacienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o id: " + id));

    // Converte a entidade encontrada diretamente para DTO
    return new PacienteDTO(
            paciente.getId(), 
            paciente.getNome(), 
            paciente.getIdade(),
            paciente.getEmail(), 
            paciente.getTelefone()
    );
    }
// Método para atualizar um Paciente(completo) - UPDATE
public PacienteDTO atualizarPacientePut(Long id, PacienteDTO pacienteDTO) {
    Paciente paciente = pacienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

    paciente.setNome(pacienteDTO.nome());
    paciente.setIdade(pacienteDTO.idade());
    paciente.setEmail(pacienteDTO.email());
    paciente.setTelefone(pacienteDTO.telefone());

    pacienteRepository.save(paciente);

    return new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getIdade(),
    paciente.getEmail(), paciente.getTelefone());
}

// Método para att um Cliente (parcial) - UPDATE
public PacienteDTO atualizarPacientePatch(Long id, PacienteDTO pacienteDTO) {
    Paciente paciente = pacienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

    if (pacienteDTO.nome() != null) {
        paciente.setNome(pacienteDTO.nome());
    }
    if(pacienteDTO.idade() != null) {
        paciente.setIdade(pacienteDTO.idade());
    }
    if (pacienteDTO.email() != null) {
        paciente.setEmail(pacienteDTO.email());
    }
    if (pacienteDTO.telefone() != null) {
        paciente.setTelefone(pacienteDTO.telefone());
    }
    pacienteRepository.save(paciente);

    return new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getIdade(),
    paciente.getEmail(), paciente.getTelefone());
}

// Método para excluir um Cliente -DELETE
public void excluirPaciente(Long id) {
    if (!pacienteRepository.existsById(id)) {
        throw new EntityNotFoundException("O paciente com id " + id + "não existe.");
    }
    pacienteRepository.deleteById(id);
}
}
package com.clinica.medvibe.service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.stereotype.Service;

import com.clinica.medvibe.dto.PacienteDTO;
import com.clinica.medvibe.model.Paciente;
import com.clinica.medvibe.repository.PacienteRepository;





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

// Metodo para obter todos os Pacientes - RESTORE(READ)
public List<PacienteDTO> listarPacientesId() {
    List<Paciente> pacienteEntity = pacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

    return pacienteEntity.stream().map(paciente -> new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getIdade(),
            paciente.getEmail(), paciente.getTelefone())).toList();

}













}

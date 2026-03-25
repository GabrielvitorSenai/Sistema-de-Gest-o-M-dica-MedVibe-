package com.clinica.medvibe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinica.medvibe.dto.MedicoDTO;
import com.clinica.medvibe.model.Medico;
import com.clinica.medvibe.repository.MedicoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    // Método para criar um novo médico - CREATE
    public MedicoDTO criarMedico(MedicoDTO medicoDTO) {
        Medico medico = new Medico();
        medico.setNome(medicoDTO.nome());
        medico.setCrm(medicoDTO.crm());
        medico.setEspecialidade(medicoDTO.especialidade());
        medico.setEmail(medicoDTO.email());
        medico.setTelefone(medicoDTO.telefone());

        Medico medicoSalvo = medicoRepository.save(medico);
        return new MedicoDTO(medicoSalvo.getId(), medicoSalvo.getNome(), medicoSalvo.getCrm(), 
                             medicoSalvo.getEspecialidade(), medicoSalvo.getEmail(), medicoSalvo.getTelefone());
    }

    // Método para obter todos os Médicos - RESTORE(READ)
    public List<MedicoDTO> listarMedicos() {
        List<Medico> medicoEntity = medicoRepository.findAll();

        return medicoEntity.stream().map(medico -> new MedicoDTO(
                medico.getId(), 
                medico.getNome(), 
                medico.getCrm(),
                medico.getEspecialidade(), 
                medico.getEmail(), 
                medico.getTelefone())).toList();
    }

    // Método para obter UM Médico por id (READ)
    public MedicoDTO listarMedicosId(Long id) {
        // Busca o médico. Se não encontrar, lança uma exceção.
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com o id: " + id));

        // Converte a entidade encontrada diretamente para DTO
        return new MedicoDTO(
                medico.getId(), 
                medico.getNome(), 
                medico.getCrm(),
                medico.getEspecialidade(), 
                medico.getEmail(), 
                medico.getTelefone()
        );
    }

    // Método para atualizar um Médico(completo) - UPDATE
    public MedicoDTO atualizarMedicoPut(Long id, MedicoDTO medicoDTO) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        medico.setNome(medicoDTO.nome());
        medico.setCrm(medicoDTO.crm());
        medico.setEspecialidade(medicoDTO.especialidade());
        medico.setEmail(medicoDTO.email());
        medico.setTelefone(medicoDTO.telefone());

        medicoRepository.save(medico);

        return new MedicoDTO(medico.getId(), medico.getNome(), medico.getCrm(),
                medico.getEspecialidade(), medico.getEmail(), medico.getTelefone());
    }

    // Método para att um Médico (parcial) - UPDATE
    public MedicoDTO atualizarMedicoPatch(Long id, MedicoDTO medicoDTO) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        if (medicoDTO.nome() != null) {
            medico.setNome(medicoDTO.nome());
        }
        if (medicoDTO.crm() != null) {
            medico.setCrm(medicoDTO.crm());
        }
        if (medicoDTO.especialidade() != null) {
            medico.setEspecialidade(medicoDTO.especialidade());
        }
        if (medicoDTO.email() != null) {
            medico.setEmail(medicoDTO.email());
        }
        if (medicoDTO.telefone() != null) {
            medico.setTelefone(medicoDTO.telefone());
        }
        
        medicoRepository.save(medico);

        return new MedicoDTO(medico.getId(), medico.getNome(), medico.getCrm(),
                medico.getEspecialidade(), medico.getEmail(), medico.getTelefone());
    }

    // Método para excluir um Médico - DELETE
    public void excluirMedico(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new EntityNotFoundException("O médico com id " + id + " não existe.");
        }
        medicoRepository.deleteById(id);
    }
}
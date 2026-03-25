package com.clinica.medvibe.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.clinica.medvibe.dto.ConsultaDTO;
import com.clinica.medvibe.model.Consulta;
import com.clinica.medvibe.repository.ConsultaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    // CREATE
    public ConsultaDTO criarConsulta(ConsultaDTO consultaDTO) {
        Consulta consulta = new Consulta();
        consulta.setDataHora(consultaDTO.dataHora());
        consulta.setTipoConsulta(consultaDTO.tipoConsulta());
        consulta.setStatus(consultaDTO.status());
        consulta.setObservacoes(consultaDTO.observacoes());

        Consulta consultaSalva = consultaRepository.save(consulta);
        return new ConsultaDTO(consultaSalva.getId(), consultaSalva.getDataHora(), 
                               consultaSalva.getTipoConsulta(), consultaSalva.getStatus(), 
                               consultaSalva.getObservacoes());
    }

    // RESTORE (ALL)
    public List<ConsultaDTO> listarConsultas() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream().map(c -> new ConsultaDTO(
                c.getId(), c.getDataHora(), c.getTipoConsulta(), c.getStatus(), c.getObservacoes()
        )).toList();
    }

    // RESTORE (ID)
    public ConsultaDTO listarConsultaId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com o id: " + id));
        return new ConsultaDTO(consulta.getId(), consulta.getDataHora(), 
                               consulta.getTipoConsulta(), consulta.getStatus(), 
                               consulta.getObservacoes());
    }

    // UPDATE PUT
    public ConsultaDTO atualizarConsultaPut(Long id, ConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setDataHora(dto.dataHora());
        consulta.setTipoConsulta(dto.tipoConsulta());
        consulta.setStatus(dto.status());
        consulta.setObservacoes(dto.observacoes());

        consultaRepository.save(consulta);
        return new ConsultaDTO(consulta.getId(), consulta.getDataHora(), 
                               consulta.getTipoConsulta(), consulta.getStatus(), 
                               consulta.getObservacoes());
    }

    // UPDATE PATCH
    public ConsultaDTO atualizarConsultaPatch(Long id, ConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        if (dto.dataHora() != null) consulta.setDataHora(dto.dataHora());
        if (dto.tipoConsulta() != null) consulta.setTipoConsulta(dto.tipoConsulta());
        if (dto.status() != null) consulta.setStatus(dto.status());
        if (dto.observacoes() != null) consulta.setObservacoes(dto.observacoes());

        consultaRepository.save(consulta);
        return new ConsultaDTO(consulta.getId(), consulta.getDataHora(), 
                               consulta.getTipoConsulta(), consulta.getStatus(), 
                               consulta.getObservacoes());
    }

    // DELETE
    public void excluirConsulta(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new EntityNotFoundException("A consulta com id " + id + " não existe.");
        }
        consultaRepository.deleteById(id);
    }
}
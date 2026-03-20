package com.clinica.medvibe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.medvibe.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}

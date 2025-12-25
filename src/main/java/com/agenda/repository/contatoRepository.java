package com.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agenda.model.contato;

@Repository
public interface contatoRepository extends JpaRepository<contato, Long> {
    
    List<contato> findByNome(String nome);
}

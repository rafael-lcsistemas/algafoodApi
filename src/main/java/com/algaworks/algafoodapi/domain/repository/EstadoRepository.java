package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    List<Estado> findByNomeContaining(String nome);
}

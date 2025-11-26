package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>, JpaSpecificationExecutor<Permissao> {

    List<Permissao> findByNomeContaining(String nome);
}

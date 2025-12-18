package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    @EntityGraph(attributePaths = {"categoria", "fabricante", "restaurante"})
    @Query("select p from Produto p ")
    Page<Produto> findTodosProdutos(Pageable pageable);

    List<Produto> findByNomeContaining(String nome);

    @Query("from Produto p where p.id = ?1")
    Optional<Produto> findById(UUID id);

    @Query("select coalesce(max(codInterno), 0) from Produto ")
    Integer getLastCodInterno();
}

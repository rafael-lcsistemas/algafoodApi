package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.Produto;
import com.algaworks.algafoodapi.domain.model.entity.ProdutoFoto;
import com.algaworks.algafoodapi.domain.repository.queries.ProdutoRepositoryQueries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID>, ProdutoRepositoryQueries {

    @EntityGraph(attributePaths = {"categoria", "fabricante", "restaurante"})
    @Query("select p from Produto p ")
    Page<Produto> findTodosProdutos(Pageable pageable);

    List<Produto> findByNomeContaining(String nome);

    @Query("from Produto p where p.id = ?1")
    Optional<Produto> findById(UUID id);

    @Query("from Produto where restaurante.id = :restaurante and id = :produto")
    Optional<Produto> findRestauranteAndProduto(@Param("restaurante") UUID idRestaurante, @Param("produto") UUID idProduto);

    @Query("select coalesce(max(codInterno), 0) from Produto ")
    Integer getLastCodInterno();

    @Query("from ProdutoFoto p where p.produto.id = :idProduto")
    Optional<ProdutoFoto> findProdutoFotoById(UUID idProduto);
}

package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.restaurante.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, UUID>, RestauranteRepositoryQueries,
        JpaSpecificationExecutor<Restaurante> {

    @Query("from Restaurante r inner join fetch r.cozinha")
    List<Restaurante> findAll();

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaMinima, BigDecimal taxaMaxima);

    List<Restaurante> findByNomeContaining(String nome);

    @Query("from Restaurante p where p.id = ?1")
    Optional<Restaurante> findById(UUID id);

    @Query("select coalesce(max(codInterno), 0) from Restaurante ")
    Integer getLastCodInterno();
}

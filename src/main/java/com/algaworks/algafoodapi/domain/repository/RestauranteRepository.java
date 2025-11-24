package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaMinima, BigDecimal taxaMaxima);

//    @Query("from Restaurante where nome like %:nome% and cozinha.id = :cozinhaId")
    List<Restaurante> consultarPorNome(String nome, Long cozinhaId);

    Optional<Restaurante> findFirstByNomeContaining(String nome);
    List<Restaurante> findTop2ByNome(String nome);
    Integer countByCozinhaNome(String nome);
}

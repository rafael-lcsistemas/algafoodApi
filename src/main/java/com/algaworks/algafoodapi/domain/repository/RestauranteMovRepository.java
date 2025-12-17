package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.restaurante.RestauranteMov;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestauranteMovRepository extends CustomJpaRepository<RestauranteMov, UUID>, JpaSpecificationExecutor<RestauranteMov> {

    @Query("from Restaurante p join fetch RestauranteMov m where m.restaurante.id = ?1")
    List<RestauranteMov> findMovimentoByIdRestaurante(UUID id);

    @Query("select coalesce(max(codInterno), 0) from RestauranteMov ")
    Integer getLastCodInterno();
}

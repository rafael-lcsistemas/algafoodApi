package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    @Query("from Usuario u left join fetch u.grupos")
    List<Usuario> findAll();

    List<Usuario> findByNomeContaining(String name);

    @Query("from Usuario p where p.id = ?1")
    Optional<Usuario> findById(UUID id);

    @Query("select coalesce(max(codInterno), 0) from Usuario ")
    Integer getLastCodInterno();
}

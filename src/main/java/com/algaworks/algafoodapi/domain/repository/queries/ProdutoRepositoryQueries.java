package com.algaworks.algafoodapi.domain.repository.queries;

import com.algaworks.algafoodapi.domain.model.entity.ProdutoFoto;

public interface ProdutoRepositoryQueries {

    ProdutoFoto save(ProdutoFoto foto);

    void delete(ProdutoFoto foto);
}

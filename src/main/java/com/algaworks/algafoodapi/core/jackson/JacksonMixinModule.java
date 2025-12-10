package com.algaworks.algafoodapi.core.jackson;

import com.algaworks.algafoodapi.api.model.mixin.CidadeMixin;
import com.algaworks.algafoodapi.api.model.mixin.GrupoMixin;
import com.algaworks.algafoodapi.api.model.mixin.ProdutoMixin;
import com.algaworks.algafoodapi.domain.model.entity.Cidade;
import com.algaworks.algafoodapi.domain.model.entity.Grupo;
import com.algaworks.algafoodapi.domain.model.entity.Produto;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {
    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
       setMixInAnnotation(Cidade.class, CidadeMixin.class);
       setMixInAnnotation(Grupo.class, GrupoMixin.class);
       setMixInAnnotation(Produto.class, ProdutoMixin.class);
    }
}

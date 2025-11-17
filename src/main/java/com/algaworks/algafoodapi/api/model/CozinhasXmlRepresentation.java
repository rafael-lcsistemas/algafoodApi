package com.algaworks.algafoodapi.api.model;

import com.algaworks.algafoodapi.domain.entity.Cozinha;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhasXmlRepresentation {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "cozinha")
    private List<Cozinha> cozinhas;

    public CozinhasXmlRepresentation(List<Cozinha> cozinhas) {
        this.cozinhas = cozinhas;
    }

    public List<Cozinha> getCozinhas() {
        return cozinhas;
    }

    public void setCozinhas(List<Cozinha> cozinhas) {
        this.cozinhas = cozinhas;
    }
}

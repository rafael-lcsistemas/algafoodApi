package com.algafood.algafoodapi.anotations;

import com.algafood.algafoodapi.enums.NivelUrgencia;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface TipoNotificador {

    NivelUrgencia value();

}

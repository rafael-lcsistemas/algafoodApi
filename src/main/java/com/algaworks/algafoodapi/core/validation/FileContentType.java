package com.algaworks.algafoodapi.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = { FileContentTypeValidator.class })
public @interface FileContentType {

    String message() default "Tipo de arquivo não suportado";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String[] allowed();
}

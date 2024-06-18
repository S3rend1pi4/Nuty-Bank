package com.nutybank.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = ExistsByDniValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByDni {
    String message() default "Ya existe, y está vinculado a otro usuario.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

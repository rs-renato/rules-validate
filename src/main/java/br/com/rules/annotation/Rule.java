package br.com.rules.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Priority;
import br.com.rules.enums.Version;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Rule {

    Priority priority() default Priority.HIGH;
    ModeloNFe modelo() default ModeloNFe.ALL;
    Version version() default Version.ALL;

}

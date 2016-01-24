package br.com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.enums.ModeloNFe;
import br.com.enums.Priority;
import br.com.enums.Version;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Rule {

    Priority priority() default Priority.HIGH;
    ModeloNFe modelo() default ModeloNFe.ALL;
    Version version() default Version.ALL;

}

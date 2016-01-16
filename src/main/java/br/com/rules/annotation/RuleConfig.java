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
public @interface RuleConfig {
	
	ModeloNFe[] models();
	
	Priority priority() default Priority.LOW;
	
	Version version() default Version.V3_10;
}

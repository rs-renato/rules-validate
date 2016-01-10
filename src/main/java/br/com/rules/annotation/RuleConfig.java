package br.com.rules.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.rules.enums.ModeloNFe;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RuleConfig {
	
	ModeloNFe[] models();
	
	int priority() default 0;
	
	float version() default 0;
}

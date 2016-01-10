package br.com.rules.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.rules.RuleValidation;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RulesChild {
	Class<? extends RuleValidation>[] rules();
}

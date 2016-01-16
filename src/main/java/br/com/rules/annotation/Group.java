package br.com.rules.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.rules.RuleValidation;
import br.com.rules.enums.Priority;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Group {
	Class<? extends RuleValidation>[] rules();
    Priority priority() default Priority.HIGH;
}

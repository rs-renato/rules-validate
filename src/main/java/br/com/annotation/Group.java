package br.com.annotation;

import br.com.enums.Priority;
import br.com.groups.GroupRules;
import br.com.rules.RuleValidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation used to define a '<i>containner group</i>' of rules,
 * represented by {@link GroupRules}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Group {

    /**
     * A {@link RuleValidation} array defined on {@link GroupRules}.
     * This array sets the rules will be performed to the group annoted by {@link Group} annotation.
     * @return a RuleValidation array.
     */
	Class<? extends RuleValidation>[] rules();

    /**
     * Defines the group's priority. The default priority is {@link Priority#LOW}
     * @return the priority.
     */
    Priority priority() default Priority.LOW;
}

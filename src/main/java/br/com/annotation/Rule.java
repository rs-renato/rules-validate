package br.com.annotation;

import br.com.enums.Model;
import br.com.enums.Priority;
import br.com.enums.Version;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to define a {@link br.com.rules.RuleValidation}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Rule {

    /**
     * Defines the rule's priority. The default priority is {@link Priority#LOW}
     * @return the priority.
     */
    Priority priority() default Priority.LOW;

    /**
     * Defines the rule's model. The default model is {@link Model#ALL}
     * @return the model
     */
    Model modelo() default Model.ALL;

    /**
     * Defines the rule's version. The default model is {@link Version#ALL}
     * @return the model
     */
    Version version() default Version.ALL;

}

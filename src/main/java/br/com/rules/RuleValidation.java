package br.com.rules;

import br.com.evaluateables.Validateable;
import br.com.exceptions.ValidateException;
import br.com.messages.ValidateableMessages;
import org.apache.log4j.Logger;

/**
 * Abstraction class to rules validation
 */
public abstract class RuleValidation<T extends Validateable>{

    private static final Logger logger = Logger.getLogger(RuleValidation.class);

    /**
     * Validate this rules, considering rules to be <i>satisfied</i> and <i>avoided</i>,
     * if there is any validation's objection. So, this rules is valid if is satisfied <b>AND</b>
     * there are no objection to the rule
     * @param validateable a validation type to be evaluated
     * @throws ValidateException throws validation expeception if any rule wasn't satisfied
     */
	public void validate(T validateable) throws ValidateException {

		if (isSatisfied(validateable) && !hasObjection(validateable)) {

			throw new ValidateException(getValidationMessage());
		}

        logger.info("Validated: " + getClass().getName());
    }

    /**
     * Retrieves the validation message to the rule
     * @return {@link ValidateableMessages} from this rule
     */
	public abstract ValidateableMessages getValidationMessage();

    /**
     * Checks all validation from this rule to be satisfied
     * @param validateable a validation type to be evaluated
     * @return true if the rule is satisfied
     */
	public abstract boolean isSatisfied(T validateable);


    /**
     * Checks all validation from this rule to be objected
     * @param validateable a validation type to be evaluated
     * @return true if the rule has any objection
     */
	public abstract boolean hasObjection(T validateable);


    /*@Override
    public String toString() {

        Rule rule = getClass().getAnnotation(Rule.class);

        return this.getClass().getSimpleName() + ":" + rule.modelo().getCodigo() + ":" + rule.version().getVersion();
    }*/
}
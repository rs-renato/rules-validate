package br.com.rules;

import br.com.annotation.Rule;
import br.com.enums.ValidationMessages;
import br.com.exceptions.ValidateException;
import br.com.wrapper.Validateable;
import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class RuleValidation{

    private static final Logger logger = Logger.getLogger(RuleValidation.class);

    private static final AtomicInteger NEXT_ID = new AtomicInteger(0);
    private final int id = NEXT_ID.getAndIncrement();

	public void validate(Validateable validateable) throws ValidateException {

		if (isSatisfied(validateable) && !hasObjection(validateable)) {

			throw new ValidateException(getValidationMessage());
		}

        logger.info("Validated: " + getClass().getName());
    }
	
	public abstract ValidationMessages getValidationMessage();
	
	public abstract boolean isSatisfied(Validateable validateable);
	
	public abstract boolean hasObjection(Validateable validateable);

    @Override
    public String toString() {

        Rule rule = getClass().getAnnotation(Rule.class);

        return this.getClass().getSimpleName() + ":" + rule.modelo().getCodigo() + ":" + rule.version().getVersion();
    }
}
package br.com.rules;

import br.com.annotation.Rule;
import br.com.enums.Message;
import br.com.exceptions.RuleException;
import br.com.wrapper.Validateable;
import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class RuleValidation{

    private static final Logger logger = Logger.getLogger(RuleValidation.class);

    private static final AtomicInteger NEXT_ID = new AtomicInteger(0);
    private final int id = NEXT_ID.getAndIncrement();

	public void validate(Validateable validateable) throws RuleException {

		if (isSatisfied(validateable) && !hasObjection(validateable)) {

			throw new RuleException(getMessage());
		}

//        System.out.println("regra validada: " + getClass().getSimpleName());
    }
	
	public abstract Message getMessage();
	
	public abstract boolean isSatisfied(Validateable validateable);
	
	public abstract boolean hasObjection(Validateable validateable);

    @Override
    public String toString() {

        Rule rule = getClass().getAnnotation(Rule.class);

        return this.getClass().getSimpleName() + ":" + rule.modelo().getCodigo() + ":" + rule.version().getVersion();
    }
}
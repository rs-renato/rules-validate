package br.com.messages;

/**
 * Defines all validation message to be showed in validate phase,
 * used in {@link br.com.rules.RuleValidation}
 */
public interface ValidateableMessages {

    /**
     * Retrieves the code of this {@link ValidateableMessages}
     * @return code
     */
	Integer getCode();

    /**
     * Retrieves the message of this {@link ValidateableMessages}
     * @return message
     */
	String getMessage();
}
package br.com.usage.message;

import br.com.enums.ValidateableMessages;

/**
 * Defines all validation message to be showed in validate phase,
 * used in {@link br.com.rules.RuleValidation}
 */
public enum Messages implements ValidateableMessages {

	REJECT_590(590, "590 message description error"),
	REJECT_805(805,"805 message description error");

	private Integer code;
	private String message;

    /**
     * Validation message constructor
     * @param code code of this validation message
     * @param message message to this validation message
     */
    Messages(Integer code, String message){
		this.code = code;
		this.message = message;
	}

    /**
     * Retrieves the code of this {@link Messages}
     * @return code
     */
	public Integer getCode(){
		return this.code;
	}

    /**
     * Retrieves the message of this {@link Messages}
     * @return message
     */
	public String getMessage(){
		return this.message;
	}
}
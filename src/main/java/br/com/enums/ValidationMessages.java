package br.com.enums;

import java.io.Serializable;

/**
 * Defines all validation messages to be showed in validate phase,
 * used in {@link br.com.rules.RuleValidation}
 */
public enum ValidationMessages implements Serializable {

	REJECT_590(590, "590 message description error"),
	REJECT_805(805,"805 message description error");
	
	private Integer code;
	private String message;

    /**
     * Validation message constructor
     * @param code code of this validation message
     * @param message message to this validation message
     */
    ValidationMessages(Integer code, String message){
		this.code = code;
		this.message = message;
	}

    /**
     * Retrieves the code of this {@link ValidationMessages}
     * @return code
     */
	public Integer getCode(){
		return this.code;
	}

    /**
     * Retrieves the message of this {@link ValidationMessages}
     * @return message
     */
	public String getMessage(){
		return this.message;
	}
}
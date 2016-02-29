package br.com.usage.message;

import br.com.enums.ValidateableMessages;

public enum Messages implements ValidateableMessages {

	REJECT_590(590, "590 message description error"),
	REJECT_805(805,"805 message description error");

	private Integer code;
	private String message;

    Messages(Integer code, String message){
		this.code = code;
		this.message = message;
	}

	public Integer getCode(){
		return this.code;
	}

    public String getMessage(){
		return this.message;
	}
}
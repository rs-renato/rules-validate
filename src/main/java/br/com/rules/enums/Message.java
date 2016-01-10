package br.com.rules.enums;

import java.io.Serializable;

public enum Message implements Serializable {

	REJECT_590(590, "590 message description error"),
	REJECT_805(805,"805 message description error");
	
	private Integer code;
	private String message;
	
	private Message(Integer code, String message){
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
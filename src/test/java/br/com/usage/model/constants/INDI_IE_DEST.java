package br.com.usage.model.constants;

public enum INDI_IE_DEST{
	
	CONTRIB_ICMS('1'),
	CONTRIB_ISENTO_ICMS('2'),
	NAO_CONTRIB('9');
	
	private char codigo;
	
	INDI_IE_DEST(char codigo) {
		this.codigo = codigo;
	}
	
	public char getCodigo() {
		return codigo;
	}
}
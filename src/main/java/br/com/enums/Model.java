package br.com.enums;

/**
 * Defines all models used in {@link br.com.annotation.Rule}
 */
public enum Model {

	MODELO_55("55"),
	MODELO_65("65"),
	ALL("mALL");

	private final String codigo;
	
	Model(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}

}

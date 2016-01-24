package br.com.enums;

public enum ModeloNFe{

	MODELO_55("55"),
	MODELO_65("65"),
	ALL("mALL");

	private String codigo;
	
	private ModeloNFe(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}

}

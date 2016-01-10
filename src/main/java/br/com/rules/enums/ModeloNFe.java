package br.com.rules.enums;

public enum ModeloNFe {

	MODELO_55(55),
	MODELO_65(65);
	
	private int codigo;
	
	private ModeloNFe(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
}

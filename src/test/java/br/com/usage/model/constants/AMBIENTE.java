package br.com.usage.model.constants;

public enum AMBIENTE {
	
	PRODUCAO(1),
	HOMOLOGACAO(2),
	DESENVOLVIMENTO(2);
	
	private int tipoAmbiente;
	
	private AMBIENTE(int tipoAmbiente) {
		this.tipoAmbiente = tipoAmbiente;
	}
	
	public int getCodigo() {
		return tipoAmbiente;
	}
}
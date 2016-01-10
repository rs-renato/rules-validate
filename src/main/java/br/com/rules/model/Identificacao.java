package br.com.rules.model;

import java.util.Date;
import java.util.List;

import br.com.rules.enums.ModeloNFe;

public class Identificacao extends EntidadeNFe {

	private char indIeDest;
	private int tipoAmbiente;
	private Date dataEmissao;
	private List<Produto> produtos;
	private boolean tagPag;
	private char codigoRegimeTributario;
	private ModeloNFe modeloNfe;
	
	public char getIndIEDest() {
		return indIeDest;
	}

	public int getTipoAmbiente() {
		return tipoAmbiente;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public char getIndIeDest() {
		return indIeDest;
	}

	public void setIndIeDest(char indIeDest) {
		this.indIeDest = indIeDest;
	}

	public void setTipoAmbiente(int tipoAmbiente) {
		this.tipoAmbiente = tipoAmbiente;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public boolean isTagPag() {
		return tagPag;
	}

	public char getCodigoRegimeTributario() {
		return codigoRegimeTributario;
	}
	
	public void setCodigoRegimeTributario(char codigoRegimeTributario) {
		this.codigoRegimeTributario = codigoRegimeTributario;
	}
	
	public ModeloNFe getModeloNFe() {
		return modeloNfe;
	}
	
	public void setModeloNFe(ModeloNFe modeloNfe) {
		this.modeloNfe = modeloNfe;
	}
}

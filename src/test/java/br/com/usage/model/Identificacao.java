package br.com.usage.model;

import br.com.enums.Model;
import br.com.enums.Version;

import java.util.Date;
import java.util.List;

public class Identificacao extends EntidadeNFe {

	private char indIeDest;
	private int tipoAmbiente;
	private Date dataEmissao;
	private List<Produto> produtos;
	private boolean tagPag;
	private char codigoRegimeTributario;
	private Model modeloNfe;
    private Version versao;

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
	
	public Model getModeloNFe() {
		return modeloNfe;
	}
	
	public void setModeloNFe(Model modeloNfe) {
		this.modeloNfe = modeloNfe;
	}

	public void setVersao(Version versao) {
		this.versao = versao;
	}

    public Version getVersao() {
        return this.versao;
    }
}

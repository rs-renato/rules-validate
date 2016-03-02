package br.com.usage.model;

import br.com.enums.Model;
import br.com.enums.Version;
import br.com.evaluateables.Validateable;

public class IdentificacaoWrapper implements Validateable {

	private Identificacao identificacao;
	private Produto produto;

	public IdentificacaoWrapper(Identificacao identificacao, Produto produto) {
		this.identificacao = identificacao;
		this.produto = produto;
	}
	
	public IdentificacaoWrapper(Identificacao identificacao) {
		this.identificacao = identificacao;
	}
	
	public IdentificacaoWrapper(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public Identificacao getIdentificacao() {
		return this.identificacao;
	}

    @Override
    public Model getModel() {
        return identificacao.getModeloNFe();
    }

    @Override
    public Version getVersion() {
        return identificacao.getVersao();
    }

}

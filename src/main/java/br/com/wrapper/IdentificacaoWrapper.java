package br.com.wrapper;

import br.com.model.Identificacao;
import br.com.model.Produto;

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
	
	@Override
	public Produto getProduto() {
		return this.produto;
	}

	@Override
	public Identificacao getIdentificacao() {
		return this.identificacao;
	}

}

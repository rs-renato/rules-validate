package br.com.usage.model;

import br.com.evaluateables.Validateable;

/**
 * Created by renatorodrigues on 05/03/16.
 */
public interface Wrapper extends Validateable{

    Produto getProduto();
    Identificacao getIdentificacao();
}
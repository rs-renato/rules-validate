package br.com.rules;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Set;

import org.reflections.Reflections;

import br.com.rules.enums.ModeloNFe;
import br.com.rules.exceptions.RuleException;
import br.com.rules.group.RuleGroup;
import br.com.rules.model.Identificacao;
import br.com.rules.model.Produto;
import br.com.rules.wrapper.IdentificacaoWrapper;
import br.com.rules.wrapper.Validateable;


public class Main {

	public static void main(String[] args) throws Exception {
		
		Produto produto = new Produto();
		produto.setCst("");
		
		Identificacao identificacao = new Identificacao();
		identificacao.setDataEmissao(new SimpleDateFormat("yyyy/mm/dd").parse("2015/12/31"));
		identificacao.setIndIeDest('1');
		identificacao.setTipoAmbiente(1);
		identificacao.setProdutos(Arrays.asList(produto));
		identificacao.setCodigoRegimeTributario('2');
		identificacao.setModeloNFe(ModeloNFe.MODELO_55);
		
		Set<Class<? extends RuleGroup>> groups = new Reflections("br.com.rules.group").getSubTypesOf(RuleGroup.class);
		
		try {

			for (Class<? extends RuleGroup> group : groups) {
				
				Validateable validateable = new IdentificacaoWrapper(identificacao);
				RuleGroup ruleContainner = group.newInstance();
				ruleContainner.execute(validateable);
				
			}
			
		} catch (RuleException e) {
			System.out.println(e.getMensagemNFe());
		}
	}
}

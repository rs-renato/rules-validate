package br.com.rules.filter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.rules.RuleValidation;
import br.com.rules.enums.ModeloNFe;



public class ModeloFilter implements Filter<ModeloNFe, Set<RuleValidation>>{

	private static Map<ModeloNFe, Set<RuleValidation>> map = new HashMap<ModeloNFe, Set<RuleValidation>>();
	
	@Override
	public Set<RuleValidation> dofilter(ModeloNFe t, Set<RuleValidation> e) {

		if (!map.containsKey(t)) {
			
			Set<RuleValidation> set = new HashSet<RuleValidation>();
			
			for (RuleValidation ruleValidation : e) {
				
				if (Arrays.asList(ruleValidation.getModeloNFe()).contains(t)) {
					set.add(ruleValidation);
				}
			}
			
			map.put(t, set);
		}
		
		return map.get(t);
	}
}

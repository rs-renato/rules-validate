package br.com.rules.containner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.rules.RuleValidation;
import br.com.rules.annotation.RulesChild;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.filter.ModeloFilter;
import br.com.rules.group.RuleGroup;

public class RulesContainner {

	private static final Map<ModeloNFe, Set<RuleValidation>> mContainner = new HashMap<ModeloNFe, Set<RuleValidation>>();
	private static final List<Class<? extends RuleGroup>> groupLoad = new ArrayList<Class<? extends RuleGroup>>();
	
	private static final Set<RuleValidation> regras = new HashSet<RuleValidation>();

	private static RulesContainner rulesContainner = new RulesContainner();

	private RulesContainner() {
	}

	public static synchronized RulesContainner getInstance() {
		return rulesContainner;
	}

	public synchronized Map<ModeloNFe, Set<RuleValidation>> getRules(Class<? extends RuleGroup> clazz) {

		if (!groupLoad.contains(clazz)) {

			try {

				if (clazz.isAnnotationPresent(RulesChild.class)) {

					for (Class<? extends RuleValidation> rule : clazz.getAnnotation(RulesChild.class).rules()) {
						
						RuleValidation r = rule.newInstance();
						
						/*if (!r.getClass().isAnnotationPresent(RuleConfig.class)) {
							continue;
						}*/
						
						regras.add(r);
						
						for (ModeloNFe model : r.getModeloNFe()) {
							
							Set<RuleValidation> rulesBymodel = mContainner.get(model);
							
							if (rulesBymodel == null) {
								rulesBymodel = new HashSet<RuleValidation>();
								mContainner.put(model, rulesBymodel);
							}
							
							mContainner.get(model).add(r);
						}
						
						/*
						final List<Method> list = new ArrayList<Method>();
						
						Collection<Method> select = CollectionUtils.select(Arrays.asList(r.getClass().getAnnotation(RuleConfig.class).getClass().getDeclaredMethods()), new Predicate<Method>() {

							@Override
							public boolean evaluate(Method method) {

								
								return false;
							}
							
							
						});*/
						
						
						
						/*for (ModeloNFe model : r.getClass().getAnnotation(RuleConfig.class).models()) {
							
							Set<RuleValidation> rulesBymodel = mContainner.get(model);
							
							if (rulesBymodel == null) {
								rulesBymodel = new HashSet<RuleValidation>();
								mContainner.put(model, rulesBymodel);
							}
							
							mContainner.get(model).add(r);
						}*/
						
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			groupLoad.add(clazz);
		}
		
		filter();
		
		return mContainner;
	}
	
	public void filter(){

		ModeloFilter modeloFilter = new ModeloFilter();
		
		Set<RuleValidation> dofilter = modeloFilter.dofilter(ModeloNFe.MODELO_55, regras);
		Set<RuleValidation> dofilter2 = modeloFilter.dofilter(ModeloNFe.MODELO_65, regras);
		
		toString();
	}
}

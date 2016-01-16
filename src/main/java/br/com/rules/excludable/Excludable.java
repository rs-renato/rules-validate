package br.com.rules.excludable;


public interface Excludable<T>{
	boolean isRuleObjection(T t);
}

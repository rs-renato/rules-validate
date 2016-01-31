package br.com.excludable;


public interface Excludable<T>{
	boolean isRuleObjection(T t);
}

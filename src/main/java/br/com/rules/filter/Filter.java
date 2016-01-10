package br.com.rules.filter;


public interface Filter <T, E> {

	public E dofilter(T t, E e);
}

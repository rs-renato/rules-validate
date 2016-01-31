package br.com.filter;


public interface Filter <T, E> {

	public E dofilter(T t, E e);
}

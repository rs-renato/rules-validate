package br.com.filter;

import java.util.Collection;

/**
 * Created by renato-rs on 14/01/2016.
 */
public interface Filterable<T extends Collection, E> {
    T doFilter(T collection, final E... types);
    T doFilter();
}
package br.com.rules.filter;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by renato-rs on 15/01/2016.
 */
public class CompositeFilter {

    public static Collection filter(Filterable... filterables){

        Collection intercection = new HashSet();

        try {

            Collection[] collections = new Collection[filterables.length];

            for (int i = 0; i < filterables.length; i++) {

                Collection filterResult = filterables[i].doFilter();
                intercection.addAll(filterResult);
                collections[i] = filterResult;
            }

            for (Collection collection : collections) {
                intercection.retainAll(collection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return intercection;
    }
}

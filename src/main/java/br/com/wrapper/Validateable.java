package br.com.wrapper;

import br.com.enums.Model;
import br.com.enums.Version;

/**
 * Validateable object to be evaluated
 */
public interface Validateable{

    /**
     * Retrieves the model of this object
     * @return {@link Model}
     */
    Model getModel();

    /**
     * Retrieves the version of this object
     * @return {@link Version}
     */
    Version getVersion();
}

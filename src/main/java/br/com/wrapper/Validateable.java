package br.com.wrapper;

import br.com.enums.Model;
import br.com.enums.Version;

public interface Validateable{

    Model getModel();
    Version getVersion();

    Object getWrapper();
}

package br.com.utils;

import br.com.enums.ModeloNFe;
import br.com.enums.Version;

/**
 * Created by renatorodrigues on 24/01/16.
 */
public class ComplexKey {

    private Integer ruleGroupId;
    private ModeloNFe modeloNFe;
    private Version version;

    public ComplexKey(Integer ruleGroupId, ModeloNFe modeloNFe, Version version) {
        this.ruleGroupId = ruleGroupId;
        this.modeloNFe = modeloNFe;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexKey that = (ComplexKey) o;

        if (ruleGroupId != null ? !ruleGroupId.equals(that.ruleGroupId) : that.ruleGroupId != null) return false;
        if (modeloNFe != that.modeloNFe) return false;
        return version == that.version;

    }

    @Override
    public int hashCode() {
        int result = ruleGroupId != null ? ruleGroupId.hashCode() : 0;
        result = 31 * result + (modeloNFe != null ? modeloNFe.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}

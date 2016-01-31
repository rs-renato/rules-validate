package br.com.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renatorodrigues on 10/01/16.
 */
public enum Version{

    V2_10("2.10"),
    V3_10("3.10"),
    ALL("vALL");

    private String version;

    private Version(String version) {
        this.version = version;
    }

    public String getVersion(){
        return this.version;
    }
}

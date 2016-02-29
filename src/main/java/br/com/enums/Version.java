package br.com.enums;

/**
 * Defines all versions used in {@link br.com.annotation.Rule}
 */
public enum Version{//FIXME change to interface

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

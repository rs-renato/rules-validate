package br.com.enums;

/**
 * Defines all versions used in {@link br.com.annotation.Rule}
 */
public enum Version{

    V2_10("2.10"),
    V3_10("3.10"),
    ALL("vALL");

    private final String version;

    Version(String version) {
        this.version = version;
    }

    public String getVersion(){
        return this.version;
    }
}

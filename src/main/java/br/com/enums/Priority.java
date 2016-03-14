package br.com.enums;

/**
 * Defines all priorities used in {@link br.com.annotation.Rule} and {@link br.com.annotation.Group}
 * The 0 value defines the higest priority, 1 for medium and 2 for lowest.
 */
public enum Priority {
    LOW(2),
    MEDIUM(1),
    HIGH(0);

    private int value;

    Priority(int value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

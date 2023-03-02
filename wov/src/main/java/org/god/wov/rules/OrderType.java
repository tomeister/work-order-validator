package org.god.wov.rules;

public enum OrderType {
    ANALYSIS("ANALYSIS"),
    REPAIR("REPAIR"),
    REPLACEMENT("REPLACEMENT");

    private final String label;

    public String getLabel() {
        return this.label;
    }

    OrderType(String label) {
        this.label = label;
    }
}

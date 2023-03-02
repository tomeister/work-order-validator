package org.god.wov.rules;

public enum ErrorCode {

    INVALID_DEPARTMENT("Department is either empty or value is not correct"),
    INVALID_CURRENCY("Currency is unrecognizable, refer to ISO 4217 codes"),
    INVALID_COST("Cost is not greater than 0"),
    INVALID_START_DATE("Start date is either empty or before current date"),
    INVALID_END_DATE("End date is either empty or before start date"),
    INVALID_FACTORY_ORDER_NUMBER("End date is either empty or before start date"),
    INVALID_FACTORY_NAME("Factory name is empty"),
    INVALID_PARTS("Empty part list or some or all parts inventory numbers are empty"),
    INVALID_ANALYSIS_DATE("Analysis date is not after start date and before end date"),
    INVALID_RESPONSIBLE_PERSON("Responsible person is empty"),
    INVALID_TEST_DATE("Test date is either empty or not after analysis date and before end date");

    private final String label;

    ErrorCode(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}

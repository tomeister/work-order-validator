package org.god.wov.rules;

public class AnalysisOrderValidation {

    public boolean departmentIsValid(String department) {
        return OrderValidationOperation.departmentIsValid(department);
    }

    public boolean startDateIsValid(String startDate) {
        return OrderValidationOperation.startDateIsValid(startDate);
    }

    public boolean endDateIsValid(String startDate, String endDate) {
        return OrderValidationOperation.endDateIsValid(startDate, endDate);
    }

    public boolean currencyIsValid(String currencyCode) {
        return OrderValidationOperation.currencyIsValid(currencyCode);
    }

    public boolean costIsValid(Double cost) {
        return OrderValidationOperation.costIsValid(cost);
    }
}

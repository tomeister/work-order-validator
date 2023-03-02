package org.god.wov.rules;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

interface OrderValidationOperation {

    static boolean departmentIsValid(String department) {
        final String ANALYSIS_DEPARTMENT = "GOoD analysis department";
        final String REPAIR_DEPARTMENT = "GOoD repair department";
        final String REPLACEMENT_DEPARTMENT = "GOoD replacement department";
        List<String> validDepartments = Arrays.asList(ANALYSIS_DEPARTMENT, REPAIR_DEPARTMENT, REPLACEMENT_DEPARTMENT);

        return department != null && validDepartments.stream().anyMatch(department::equalsIgnoreCase);
    }

    static boolean startDateIsValid(String startDate) {
        if (startDate == null || startDate.isEmpty()) {
            return false;
        }

        return LocalDate.parse(startDate).isBefore(LocalDate.now());
    }

    static boolean endDateIsValid(String startDate, String endDate) {
        if (startDate == null || endDate == null) {
            return false;
        }
        if (startDate.isEmpty() || endDate.isEmpty()) {
            return false;
        }

        return LocalDate.parse(endDate).isAfter(LocalDate.parse(startDate));
    }

    static boolean currencyIsValid(String currencyCode) {
        try {
            Currency.getInstance(currencyCode);
            return true;
        } catch (IllegalArgumentException incorrectCurrency) {
            return false;
        }
    }

    static boolean costIsValid(Double cost) {
        return cost != null && cost > 0;
    }

}

package org.god.wov.rules;

import java.util.List;

public class RepairOrder extends Order {

    final String analysisDate;
    final String testDate;
    final String responsiblePerson;

    public RepairOrder(String department, String startDate, String endDate, String currency, Double cost,
                       List<Part> parts, String analysisDate, String testDate, String responsiblePerson) {
        super(department, startDate, endDate, currency, cost, parts);
        this.analysisDate = analysisDate;
        this.testDate = testDate;
        this.responsiblePerson = responsiblePerson;
    }
}

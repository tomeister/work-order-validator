package org.god.wov.rules;

import java.util.List;

public class AnalysisOrder extends Order {

    public AnalysisOrder(String department, String startDate, String endDate, String currency, Double cost, List<Part> parts) {
        super(department, startDate, endDate, currency, cost, parts);
    }
}

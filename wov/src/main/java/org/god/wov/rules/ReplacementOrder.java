package org.god.wov.rules;

import java.util.List;

public class ReplacementOrder extends Order {

    final String factoryName;
    final String factoryOrderNumber;

    public ReplacementOrder(String department, String startDate, String endDate, String currency, Double cost,
                            List<Part> parts, String factoryName, String factoryOrderNumber) {
        super(department, startDate, endDate, currency, cost, parts);
        this.factoryName = factoryName;
        this.factoryOrderNumber = factoryOrderNumber;
    }
}

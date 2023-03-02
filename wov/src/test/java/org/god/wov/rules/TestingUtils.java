package org.god.wov.rules;

import org.god.wov.rules.AnalysisOrder;
import org.god.wov.rules.Part;
import org.god.wov.rules.RepairOrder;
import org.god.wov.rules.ReplacementOrder;

import java.util.List;

public class TestingUtils {

    private TestingUtils() {}

    static AnalysisOrder generateAnalysisOrder(String department, String startDate, String endDate, String currency, Double cost, List<Part> parts) {
        return new AnalysisOrder(department, startDate, endDate, currency, cost, parts);
    }

    public static RepairOrder generateRepairOrder(String department, String startDate, String endDate, String currency, Double cost,
                                                  List<Part> parts, String analysisDate, String testDate, String responsiblePerson) {
        return new RepairOrder(department, startDate, endDate, currency, cost, parts, analysisDate, testDate, responsiblePerson);
    }

    static ReplacementOrder generateReplacementOrder(String department, String startDate, String endDate, String currency, Double cost,
                                                     List<Part> parts, String factoryName, String factoryOrderNumber) {
        return new ReplacementOrder(department, startDate, endDate, currency, cost, parts, factoryName, factoryOrderNumber);
    }
}

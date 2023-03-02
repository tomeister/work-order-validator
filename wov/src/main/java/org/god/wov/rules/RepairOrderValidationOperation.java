package org.god.wov.rules;

import java.util.List;

interface RepairOrderValidationOperation {

    boolean analysisDateIsValid(String startDate, String endDate, String analysisDate);
    boolean responsiblePersonIsValid(String name);
    boolean testDateIsValid(String analysisDate, String endDate, String testDate);
    boolean partsAreValid(List<Part> parts);
}

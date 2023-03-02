package org.god.wov.rules;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

public class RepairOrderValidation implements RepairOrderValidationOperation {

    @Override
    public boolean analysisDateIsValid(String startDate, String endDate, String analysisDate) {
        return subjectDateIsValid(startDate, endDate, analysisDate);
    }

    @Override
    public boolean responsiblePersonIsValid(String name) {
        return name != null && name.length() > 0;
    }

    @Override
    public boolean testDateIsValid(String analysisDate, String endDate, String testDate) {
        return subjectDateIsValid(analysisDate, endDate, testDate);
    }

    @Override
    public boolean partsAreValid(List<Part> parts) {
        return !parts.isEmpty();
    }

    private boolean subjectDateIsValid(String firstDate, String lastDate, String subjectDate) {
        if (dateValueIsInvalid(firstDate) || dateValueIsInvalid(lastDate) || dateValueIsInvalid(subjectDate)) {
            return false;
        }

        LocalDate midDate = LocalDate.parse(subjectDate);
        return midDate.isAfter(LocalDate.parse(firstDate)) && midDate.isBefore(LocalDate.parse(lastDate));
    }

    private boolean dateValueIsInvalid(String date) {
        if (date == null) {
            return true;
        }
        if (date.isEmpty()) {
            return true;
        }

        return dateFormatIsInvalid(date);
    }

    private boolean dateFormatIsInvalid(String date) {
        final String VALID_PATTERN = "uuuu-M-d";
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(VALID_PATTERN).withResolverStyle(ResolverStyle.STRICT));
            return false;
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return true;
        }
    }
}

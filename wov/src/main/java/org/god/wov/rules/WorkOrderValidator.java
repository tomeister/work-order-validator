package org.god.wov.rules;

import java.util.List;

public class WorkOrderValidator {

    private final AnalysisOrderValidation analysisOrderValidation = new AnalysisOrderValidation();
    private final ReplacementOrderValidation replacementOrderValidation = new ReplacementOrderValidation();
    private final RepairOrderValidationOperation repairOrderValidation = new RepairOrderValidation();
    public void validateWorkOrder(Order order, List<ErrorCode> errorCodes) {
        if (order instanceof AnalysisOrder) {
            validateAnalysisOrder(order, errorCodes);
        }
        if (order instanceof RepairOrder) {
            validateRepairOrder((RepairOrder) order, errorCodes);
        }
        if (order instanceof ReplacementOrder) {
            validateReplacementOrder((ReplacementOrder) order, errorCodes);
        }
    }

    private void validateAnalysisOrder(Order order, List<ErrorCode> errorCodes) {
        if(!analysisOrderValidation.departmentIsValid(order.department)) {
            errorCodes.add(ErrorCode.INVALID_DEPARTMENT);
        }
        if(!analysisOrderValidation.currencyIsValid(order.currency)) {
            errorCodes.add(ErrorCode.INVALID_CURRENCY);
        }
        if(!analysisOrderValidation.costIsValid(order.cost)) {
            errorCodes.add(ErrorCode.INVALID_COST);
        }
        if(!analysisOrderValidation.startDateIsValid(order.startDate)) {
            errorCodes.add(ErrorCode.INVALID_START_DATE);
        }
        if(!analysisOrderValidation.endDateIsValid(order.startDate, order.endDate)) {
            errorCodes.add(ErrorCode.INVALID_END_DATE);
        }
    }

    private void validateReplacementOrder(ReplacementOrder order, List<ErrorCode> errorCodes) {
        validateAnalysisOrder(order, errorCodes);
        if(!replacementOrderValidation.factoryOrderNumberIsValid(order.factoryOrderNumber)) {
            errorCodes.add(ErrorCode.INVALID_FACTORY_ORDER_NUMBER);
        }
        if(!replacementOrderValidation.factoryNameIsValid(order.factoryName)) {
            errorCodes.add(ErrorCode.INVALID_FACTORY_NAME);
        }
        if(!replacementOrderValidation.allPartsAreValid(order.parts)) {
            errorCodes.add(ErrorCode.INVALID_PARTS);
        }
    }

    private void validateRepairOrder(RepairOrder order, List<ErrorCode> errorCodes) {
        validateAnalysisOrder(order, errorCodes);
        if(!repairOrderValidation.analysisDateIsValid(order.startDate, order.endDate, order.analysisDate)) {
            errorCodes.add(ErrorCode.INVALID_ANALYSIS_DATE);
        }
        if(!repairOrderValidation.partsAreValid(order.parts)) {
            errorCodes.add(ErrorCode.INVALID_PARTS);
        }
        if(!repairOrderValidation.responsiblePersonIsValid(order.responsiblePerson)) {
            errorCodes.add(ErrorCode.INVALID_RESPONSIBLE_PERSON);
        }
        if(!repairOrderValidation.testDateIsValid(order.analysisDate, order.endDate, order.testDate)) {
            errorCodes.add(ErrorCode.INVALID_TEST_DATE);
        }
    }
}

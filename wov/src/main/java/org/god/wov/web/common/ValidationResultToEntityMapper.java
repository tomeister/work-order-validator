package org.god.wov.web.common;

import org.god.wov.web.common.model.WorkOrderValidationResult;
import org.god.wov.web.common.model.request.WorkOrderSingleRequest;

import static java.time.LocalDate.now;

public class ValidationResultToEntityMapper {

    WorkOrderValidationResult validationResultToValidationResultEntity(WorkOrderSingleRequest inboundOrder, Validity validity) {
        String currentDate = now().toString();
        String type = inboundOrder.type();
        String department = inboundOrder.department();
        String status = validity.name();

        return new WorkOrderValidationResult(currentDate, status, type, department);
    }

}

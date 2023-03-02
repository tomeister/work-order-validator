package org.god.wov.web.single;

import lombok.extern.log4j.Log4j2;
import org.god.wov.rules.*;
import org.god.wov.web.common.ValidationError;
import org.god.wov.web.common.Validity;
import org.god.wov.web.common.WorkOrderValidationDatabaseService;
import org.god.wov.web.common.model.WorkOrderValidationResult;
import org.god.wov.web.common.model.request.WorkOrderSingleRequest;
import org.god.wov.web.common.model.response.WorkOrderSingleResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;
import static org.god.wov.web.common.Validity.INVALID;
import static org.god.wov.web.common.Validity.VALID;

@Log4j2
@Service
public class SingleOrderService {
    private final WorkOrderValidator validator;
    private final WorkOrderValidationDatabaseService databaseOps;
    public SingleOrderService(WorkOrderValidator validator, WorkOrderValidationDatabaseService databaseOps) {
        this.validator = validator;
        this.databaseOps = databaseOps;
    }

    WorkOrderSingleResponse validate(WorkOrderSingleRequest inboundOrder) {
        Validity validity = VALID;
        List<ErrorCode> errorCodes = new ArrayList<>();
        List<ValidationError> validationErrors = new ArrayList<>();
        validator.validateWorkOrder(determineOrderType(inboundOrder), errorCodes);

        if (!errorCodes.isEmpty()) {
           validationErrors = errorCodes.stream().map(
                    errorCode -> new ValidationError(errorCode.toString(), errorCode.getLabel())).toList();
           validity = INVALID;
        }
        log.info("Validated. Errors: {}", validationErrors);

        databaseOps.saveWorkOrderValidationResult(inboundOrder, validity);
        log.info("Successfully stored validated work order in the database: status - {}, order - {}", validity.name(), inboundOrder);

        return new WorkOrderSingleResponse(validity.getLabel(), validationErrors);
    }

    private Order determineOrderType(WorkOrderSingleRequest order) {
        return switch (OrderType.valueOf(order.type().toUpperCase())) {
            case REPAIR -> new RepairOrder(order.department(), order.start_date(), order.end_date(), order.currency(),
                    order.cost(), order.parts(), order.analysis_date(), order.test_date(), order.responsible_person());
            case REPLACEMENT -> new ReplacementOrder(order.department(), order.start_date(), order.end_date(),
                    order.currency(), order.cost(), order.parts(), order.factory_name(), order.factory_order_number());
            default -> new AnalysisOrder(order.department(), order.start_date(), order.end_date(),
                    order.currency(), order.cost(), order.parts());
        };
    }
}

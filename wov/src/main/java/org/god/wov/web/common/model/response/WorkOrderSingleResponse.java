package org.god.wov.web.common.model.response;

import org.god.wov.web.common.ValidationError;

import java.util.List;

public record WorkOrderSingleResponse(String validationResult, List<ValidationError> validationErrors) {
}

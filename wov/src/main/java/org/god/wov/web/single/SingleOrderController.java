package org.god.wov.web.single;

import static org.god.wov.web.single.SingleOrderController.REQUEST_ROOT;

import lombok.extern.log4j.Log4j2;
import org.god.wov.web.common.model.WorkOrderValidationResult;
import org.god.wov.web.common.model.request.WorkOrderSingleRequest;
import org.god.wov.web.common.model.response.WorkOrderSingleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(REQUEST_ROOT)
public class SingleOrderController {
    static final String REQUEST_ROOT = "/api";
    private static final String REQUEST_VALIDATE_SINGLE = "/order";
    private final SingleOrderService singleOrderService;

    public SingleOrderController(SingleOrderService singleOrderService) {
        this.singleOrderService = singleOrderService;
    }

    @PostMapping(value = REQUEST_VALIDATE_SINGLE)
    public @ResponseBody ResponseEntity<WorkOrderSingleResponse> validateSingleOrder(@Valid @RequestBody WorkOrderSingleRequest requestBody) {
        log.info("Request body: {}", requestBody);
        return new ResponseEntity<>(singleOrderService.validate(requestBody), HttpStatus.OK);
    }
}

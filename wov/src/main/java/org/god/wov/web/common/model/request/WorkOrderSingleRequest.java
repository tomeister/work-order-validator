package org.god.wov.web.common.model.request;

import org.god.wov.rules.Part;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public record WorkOrderSingleRequest(@NotNull String type, @NotNull String department, @NotNull String start_date, @NotNull String end_date, @NotNull String currency,
                                     @NotNull Double cost, @NotEmpty List<Part> parts, String analysis_date, String test_date,
                                     String responsible_person, String factory_name, String factory_order_number) {}
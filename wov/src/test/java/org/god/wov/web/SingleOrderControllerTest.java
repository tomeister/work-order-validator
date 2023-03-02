package org.god.wov.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.god.wov.rules.*;
import org.god.wov.web.common.model.request.WorkOrderSingleRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.god.wov.rules.TestingUtils.generateRepairOrder;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SingleOrderControllerTest {

  private static ObjectMapper MAPPER;
  @Autowired
  private MockMvc mvc;

  @BeforeAll
  static void setUp() {
    MAPPER = new ObjectMapper();
  }


  @Test
  void returnsValidResponse_whenRepairOrderIsValid() throws Exception {
    List<ErrorCode> errorCodes = new ArrayList<>();
    WorkOrderSingleRequest order = genInboundWorkOrder();
    String requestBody = MAPPER.writeValueAsString(order);

    RepairOrder repairOrder = generateRepairOrder(order.department(), order.start_date(), order.end_date(), order.currency(),
            order.cost(), order.parts(), order.analysis_date(), order.test_date(), order.responsible_person());
    WorkOrderValidator mockValidator = spy(new WorkOrderValidator());
    doNothing().when(mockValidator).validateWorkOrder(repairOrder, errorCodes);

    mvc.perform(post("/api/order").contentType("application/json").content(requestBody)).andExpect(status().isOk());
  }

  @Test
  void returnsBadRequestResponse_whenRepairOrderIsInvalid() throws Exception {
    WorkOrderSingleRequest request = new WorkOrderSingleRequest(null, null, null, null, null, null,
            null, null, null, null, null, null);
    String requestBody = MAPPER.writeValueAsString(request);

    mvc.perform(post("/api/order").contentType("application/json").content(requestBody))
        .andExpect(status().isBadRequest());
  }

  private WorkOrderSingleRequest genInboundWorkOrder() {
    String type = "REPAIR";
    String department = "GOoD analysis department";
    String startDate = "2020-08-13";
    String endDate = "2020-08-16";
    String currency = "USD";
    String analysisDate = "2020-08-14";
    String testDate = "2020-08-15";
    String responsiblePerson = "GOoD repairmaster";
    String factoryName = "GOoDfactory";
    String factoryOrderNumber = "DE12345678";
    Double cost = 123.12;
    List<Part> parts = List.of(new Part("InventoryNumber3", "PartNumber3", 3), new Part("InventoryNumber4", "PartNumber4", 4));

    return new WorkOrderSingleRequest(type, department, startDate, endDate, currency, cost, parts, analysisDate, testDate,
            responsiblePerson, factoryName, factoryOrderNumber);
  }
}

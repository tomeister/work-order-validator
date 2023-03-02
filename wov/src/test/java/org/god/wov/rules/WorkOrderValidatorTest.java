package org.god.wov.rules;

import static org.god.wov.rules.TestingUtils.*;
import static org.god.wov.rules.TestingUtils.generateRepairOrder;
import static org.god.wov.rules.TestingUtils.generateReplacementOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkOrderValidatorTest {
  private WorkOrderValidator validator;

  private final String START_DATE = "2020-08-13";
  private final String END_DATE = "2020-08-16";
  private final String ANALYSIS_DATE = "2020-08-14";
  private final String TEST_DATE = "2020-08-15";
  private final String CURRENCY = "USD";
  private final Double COST = 123.12;

  @BeforeEach
  void setUp() {
    validator = new WorkOrderValidator();
  }

  @Test
  void validAnalysisWorkOrderShouldHaveValidTrueTest() {
    List<ErrorCode> errorCodes = new ArrayList<>();
    String department = "GOoD analysis department";
    List<Part> parts = List.of(new Part("InventoryNumber1", "PartNumber1", 1), new Part("InventoryNumber2", "PartNumber2", 2));

    AnalysisOrder analysisOrder = generateAnalysisOrder(department, START_DATE, END_DATE, CURRENCY, COST, parts);
    validator.validateWorkOrder(analysisOrder, errorCodes);

    assertTrue(errorCodes.isEmpty());
  }

  @Test
  void validReplacementWorkOrderShouldHaveValidTrueTest() {
    List<ErrorCode> errorCodes = new ArrayList<>();
    String department = "GOoD analysis department";
    String factoryName = "GOoDfactory";
    String factoryOrderNumber = "DE12345678";
    List<Part> parts = List.of(new Part("InventoryNumber5", "PartNumber5", 5), new Part("InventoryNumber6", "PartNumber6", 6));

    ReplacementOrder replacementOrder = generateReplacementOrder(department, START_DATE, END_DATE, CURRENCY, COST, parts, factoryName, factoryOrderNumber);
    validator.validateWorkOrder(replacementOrder, errorCodes);

    assertTrue(errorCodes.isEmpty());
  }

  @Test
  void validRepairWorkOrderShouldHaveValidTrueTest() {
    List<ErrorCode> errorCodes = new ArrayList<>();
    String department = "GOoD analysis department";
    String responsiblePerson = "GOoD repairmaster";
    List<Part> parts = List.of(new Part("InventoryNumber3", "PartNumber3", 3), new Part("InventoryNumber4", "PartNumber4", 4));

    RepairOrder repairOrder = generateRepairOrder(department, START_DATE, END_DATE, CURRENCY, COST, parts, ANALYSIS_DATE, TEST_DATE, responsiblePerson);
    validator.validateWorkOrder(repairOrder, errorCodes);

    assertTrue(errorCodes.isEmpty());
  }

  @Test
  void invalidAnalysisWorkOrderStartDateShouldHaveInvalidTest() {
    List<ErrorCode> errorCodes = new ArrayList<>();
    String department = "GOoD analysis department";
    String startDate = "2040-08-11";
    List<Part> parts = List.of(new Part("InventoryNumber1", "PartNumber1", 1), new Part("InventoryNumber2", "PartNumber2", 2));

    AnalysisOrder analysisOrder = generateAnalysisOrder(department, startDate, END_DATE, CURRENCY, COST, parts);
    validator.validateWorkOrder(analysisOrder, errorCodes);

    assertTrue(errorCodes.contains(ErrorCode.INVALID_START_DATE));
  }

  @Test
  void invalidReplacementWorkOrderFactoryOrderNumberShouldHaveInvalidTest() {
    List<ErrorCode> errorCodes = new ArrayList<>();
    String department = "GOoD analysis department";
    String factoryName = "GOoDfactory";
    String factoryOrderNumber = "D31234567B";
    List<Part> parts = List.of(new Part("InventoryNumber5", "PartNumber5", 5), new Part("InventoryNumber6", "PartNumber6", 6));

    ReplacementOrder replacementOrder = generateReplacementOrder(department, START_DATE, END_DATE, CURRENCY, COST, parts, factoryName, factoryOrderNumber);
    validator.validateWorkOrder(replacementOrder, errorCodes);

    assertTrue(errorCodes.contains(ErrorCode.INVALID_FACTORY_ORDER_NUMBER));
  }

  @Test
  void invalidRepairWorkOrderResponsiblePersonShouldHaveInvalidTest() {
    List<ErrorCode> errorCodes = new ArrayList<>();
    String department = "GOoD analysis department";
    String responsiblePerson = "";
    List<Part> parts = List.of(new Part("InventoryNumber3", "PartNumber3", 3), new Part("InventoryNumber4", "PartNumber4", 4));

    RepairOrder repairOrder = generateRepairOrder(department, START_DATE, END_DATE, CURRENCY, COST, parts, ANALYSIS_DATE, TEST_DATE, responsiblePerson);
    validator.validateWorkOrder(repairOrder, errorCodes);

    assertTrue(errorCodes.contains(ErrorCode.INVALID_RESPONSIBLE_PERSON));
  }
}
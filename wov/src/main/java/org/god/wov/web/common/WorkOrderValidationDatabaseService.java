package org.god.wov.web.common;

import lombok.extern.log4j.Log4j2;
import org.god.wov.web.common.model.WorkOrderValidationResult;
import org.god.wov.web.common.model.request.WorkOrderSingleRequest;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class WorkOrderValidationDatabaseService {

  private final WorkOrderValidationRepository workOrderValidationRepository;
  private final ValidationResultToEntityMapper mapper;

  public WorkOrderValidationDatabaseService(WorkOrderValidationRepository workOrderValidationRepository, ValidationResultToEntityMapper mapper) {
    this.workOrderValidationRepository = workOrderValidationRepository;
    this.mapper = mapper;
  }

  public void saveWorkOrderValidationResult(WorkOrderSingleRequest inboundOrder, Validity validity) {
    WorkOrderValidationResult validationResult = mapper.validationResultToValidationResultEntity(inboundOrder, validity);
    workOrderValidationRepository.save(validationResult);
  }
}

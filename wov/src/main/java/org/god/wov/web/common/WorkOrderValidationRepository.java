package org.god.wov.web.common;

import org.god.wov.web.common.model.WorkOrderValidationResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderValidationRepository extends CrudRepository<WorkOrderValidationResult, String> {
}

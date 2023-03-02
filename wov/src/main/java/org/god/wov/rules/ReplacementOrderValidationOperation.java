package org.god.wov.rules;

import java.util.List;

interface ReplacementOrderValidationOperation {

    boolean factoryNameIsValid(String name);
    boolean factoryOrderNumberIsValid(String orderNumber);
    boolean allPartsAreValid(List<Part> parts);
}

package org.god.wov.rules;

import java.util.List;

public class ReplacementOrderValidation implements ReplacementOrderValidationOperation {

    @Override
    public boolean factoryNameIsValid(String name) {
        if (name == null) {
            return false;
        }
        return name.length() > 0;
    }

    @Override
    public boolean factoryOrderNumberIsValid(String orderNumber) {
        if (orderNumber.length() != 10) {
            return false;
        }

        char[] orderNoChars = orderNumber.toCharArray();
        if (Character.isDigit(orderNoChars[0]) || Character.isDigit(orderNoChars[1])) {
            return false;
        }

        for (int i = 2; i < orderNoChars.length; i++) {
            if (Character.isLetter(orderNoChars[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean allPartsAreValid(List<Part> parts) {
        for (Part part : parts) {
            if (part.inventory_number().length() == 0) {
                return false;
            }
        }

        return true;
    }
}

package org.god.wov.rules;

import java.util.List;

public class Order {
    final String department;
    final String startDate;
    final String endDate;
    final String currency;
    final Double cost;
    final List<Part> parts;

    public Order(String department, String startDate, String endDate, String currency, Double cost, List<Part> parts) {
        this.department = department;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currency = currency;
        this.cost = cost;
        this.parts = parts;
    }

    public String getDepartment() {
        return department;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCurrency() {
        return currency;
    }

    public Double getCost() {
        return cost;
    }

    public List<Part> getParts() {
        return parts;
    }

    @Override
    public String toString() {
        return "Order{" +
                ", department='" + department + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", currency='" + currency + '\'' +
                ", cost=" + cost +
                ", parts=" + parts +
                '}';
    }
    
}

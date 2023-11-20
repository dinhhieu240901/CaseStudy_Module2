package com.codegym.model.person.employee;

import com.codegym.model.person.enumerations.GenderPerson;

public class WarehouseManager extends Employee {

    public WarehouseManager(String name, String streetAddress, String city, String country, GenderPerson gender, int age, String employeeId, String jobPosition, double salary, String username, String password, double totalMoney) {
        super(name, streetAddress, city, country, gender, age, employeeId, jobPosition, salary, "WarehouseManager", username, password, totalMoney);
    }
}

package com.codegym.model.person.employee;

import com.codegym.model.person.enumerations.GenderPerson;
import com.codegym.model.ticket.Ticket;
import java.util.List;

public class Accountant extends Employee {




    public Accountant(String name, String streetAddress, String city, String country, GenderPerson gender, int age, String employeeId, String jobPosition, double salary,String username, String password, double totalMoney) {
        super(name, streetAddress, city, country, gender, age, employeeId, jobPosition, salary,"Accountant",username,password,totalMoney);
    }
}

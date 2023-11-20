package com.codegym.model.person.visitors;

import com.codegym.model.person.Person;
import com.codegym.model.person.enumerations.AgeCategory;
import com.codegym.model.person.enumerations.GenderPerson;
import com.codegym.model.ticket.SeniorTicket;

public class Senior extends Visitor {
    private SeniorTicket seniorTicket;

    public Senior(String visitorID, String name, String address, GenderPerson genderPerson, int age, AgeCategory ageCategory, SeniorTicket seniorTicket) {
        super(visitorID, name, address, genderPerson, age, ageCategory);
        this.seniorTicket = seniorTicket;
    }

    public Senior(String visitorID, String address, GenderPerson genderPerson, int age, AgeCategory ageCategory, SeniorTicket seniorTicket) {
        super(visitorID, address, genderPerson, age, ageCategory);
        this.seniorTicket = seniorTicket;
    }

    public SeniorTicket getSeniorTicket() {
        return seniorTicket;
    }

    public void setSeniorTicket(SeniorTicket seniorTicket) {
        this.seniorTicket = seniorTicket;
    }

    @Override
    public String toString() {
        return super.toString() + seniorTicket;
    }
}

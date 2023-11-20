package com.codegym.model.person.visitors;

import com.codegym.model.person.Person;
import com.codegym.model.person.enumerations.AgeCategory;
import com.codegym.model.person.enumerations.GenderPerson;
import com.codegym.model.ticket.ChildTicket;

public class Child extends Visitor {
    private ChildTicket childTicket;

    public Child(String visitorID, String name, String address, GenderPerson genderPerson, int age, AgeCategory ageCategory, ChildTicket childTicket) {
        super(visitorID, name, address, genderPerson, age, ageCategory);
        this.childTicket = childTicket;
    }

    public Child(String visitorID, String address, GenderPerson genderPerson, int age, AgeCategory ageCategory, ChildTicket childTicket) {
        super(visitorID, address, genderPerson, age, ageCategory);
        this.childTicket = childTicket;
    }

    public ChildTicket getChildTicket() {
        return childTicket;
    }

    public void setChildTicket(ChildTicket childTicket) {
        this.childTicket = childTicket;
    }

    @Override
    public String toString() {
        return super.toString() + childTicket;
    }
}

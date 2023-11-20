package com.codegym.model.person.visitors;

import com.codegym.model.person.Person;
import com.codegym.model.person.enumerations.AgeCategory;
import com.codegym.model.person.enumerations.GenderPerson;
import com.codegym.model.ticket.AdultTicket;

public class Adult extends Visitor {
    private AdultTicket adultTicket;

    public Adult(String visitorID, String name, String address, GenderPerson genderPerson, int age, AgeCategory ageCategory, AdultTicket adultTicket) {
        super(visitorID, name, address, genderPerson, age, ageCategory);
        this.adultTicket = adultTicket;
    }


    public AdultTicket getAdultTicket() {
        return adultTicket;
    }

    public void setAdultTicket(AdultTicket adultTicket) {
        this.adultTicket = adultTicket;
    }

    @Override
    public String toString() {
        return super.toString() + adultTicket;
    }
}

package com.codegym.model.ticket;

public class FactoryTicket {
    public static Ticket getTicket(int age){
            if(age<=12){
                return new ChildTicket();
            }
            else if(age <=64){
                return new AdultTicket();
            }
            else {
                return new SeniorTicket();
            }
     }
}

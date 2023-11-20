package com.codegym.model.ticket;

import com.codegym.model.ticket.enumerations.Date;

public class AdultTicket implements Ticket{
    @Override
    public double getPrice() {
        return 600;
    }

    @Override
    public String toString() {
        return ", AdultTicket=" + getPrice() +
                '}';
    }
}

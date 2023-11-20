package com.codegym.Test;

import com.codegym.model.person.enumerations.AgeCategory;
import com.codegym.model.person.enumerations.GenderPerson;
import com.codegym.model.person.visitors.Adult;
import com.codegym.model.person.visitors.Child;
import com.codegym.model.person.visitors.Senior;
import com.codegym.model.person.visitors.Visitor;
import com.codegym.model.ticket.AdultTicket;
import com.codegym.model.ticket.ChildTicket;
import com.codegym.model.ticket.SeniorTicket;
import com.codegym.service.VisitorService;

import java.util.ArrayList;
import java.util.List;

public class visitor {
    private static VisitorService visitorService =  VisitorService.getVisitorService();
    public static void main(String[] args) {
        visitorService.addVisitor(new Adult("KH02","Nam","Biên Hòa", GenderPerson.MALE,24, AgeCategory.ADULT,new AdultTicket()));
        visitorService.addVisitor(new Child("KH03","Hoa","Hà Nội", GenderPerson.FEMALE,10, AgeCategory.CHILD,new ChildTicket()));
        visitorService.addVisitor(new Child("KH04","Binh","Đà Nẵng", GenderPerson.MALE,8, AgeCategory.CHILD,new ChildTicket()));
        visitorService.addVisitor(new Senior("KH05","Huong","Hồ Chí Minh", GenderPerson.FEMALE,70, AgeCategory.SENIOR,new SeniorTicket()));
        visitorService.addVisitor(new Senior("KH06","Phong","Cần Thơ", GenderPerson.MALE,72, AgeCategory.SENIOR,new SeniorTicket()));

        visitorService.addVisitor(new Adult("KH07","Lan","Nha Trang", GenderPerson.FEMALE,30, AgeCategory.ADULT,new AdultTicket()));
        visitorService.addVisitor(new Adult("KH08","Mai","Đà Lạt", GenderPerson.FEMALE,32, AgeCategory.ADULT,new AdultTicket()));

        visitorService.addVisitor(new Child("KH09","Anh","Vũng Tàu", GenderPerson.FEMALE,12, AgeCategory.CHILD,new ChildTicket()));
        visitorService.addVisitor(new Child("KH10","Hằng","Phú Quốc", GenderPerson.FEMALE,7, AgeCategory.CHILD,new ChildTicket()));

        visitorService.addVisitor(new Senior("KH11","Hoa","Sapa", GenderPerson.FEMALE,75, AgeCategory.SENIOR,new SeniorTicket()));


    }
}

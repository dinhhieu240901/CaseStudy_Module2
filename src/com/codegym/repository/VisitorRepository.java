package com.codegym.repository;

import com.codegym.model.person.enumerations.AgeCategory;
import com.codegym.model.person.visitors.Visitor;
import com.codegym.serializer.ReadCustomerSerializer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VisitorRepository {

    private static List<Visitor> visitors;

    private static VisitorRepository visitorRepository;

    private ReadCustomerSerializer readCustomerSerializer = ReadCustomerSerializer.getInstanceReadCustomerSerializer();

    private VisitorRepository() {
        this.visitors = readCustomerSerializer.readFromCSV();
    }

    public static VisitorRepository getVisitorRepository(){
        if (visitorRepository == null){
            visitorRepository = new VisitorRepository();
        }
        return visitorRepository;
    }




    public void addVisitor(Visitor visitor) {
        if (!isVisitorIdExist(visitor.getVisitorID())) {
            visitors.add(visitor);
            updateFileCSV();
        } else {
            System.out.println("Mã khách hàng" + visitor.getVisitorID() + " đã tồn tại.");
        }
    }
    public void removeVisitor(String visitorId) {
        visitors.removeIf(visitor -> visitor.getVisitorID().equals(visitorId));
        updateFileCSV();
    }
    private boolean isVisitorIdExist(String visitorId) {
        for (Visitor existingVisitor : visitors) {
            if (existingVisitor.getVisitorID().equals(visitorId)) {
                return true;
            }
        }
        return false;
    }

    public List<Visitor> findVisitorsByAgeCategory(AgeCategory ageCategory) {
        List<Visitor> matchingVisitors = new ArrayList<>();
        for (Visitor visitor : visitors) {
            if (visitor.getAgeCategory() == ageCategory) {
                matchingVisitors.add(visitor);
            }
        }
        return matchingVisitors;
    }
    public static Visitor findVisitorById(String visitorId) {
        for (Visitor visitor : visitors) {
            if (visitor.getVisitorID().equals(visitorId)) {
                return visitor;
            }
        }
        return null;
    }


    public List<Visitor> getAllVisitors() {
        return visitors;
    }

    public void updateVisitor(Visitor updatedVisitor) {
        for (int i = 0; i < visitors.size(); i++) {
            if (visitors.get(i).getVisitorID().equals(updatedVisitor.getVisitorID())) {
                visitors.set(i, updatedVisitor);
                updateFileCSV();
                return;
            }
        }
    }
    public void sortVisitorsByName() {
        visitors.sort(Comparator.comparing(Visitor::getName));
        updateFileCSV();
    }
    private void updateFileCSV(){
        readCustomerSerializer.writeToCSV(visitors);
    }
}

package com.codegym.serializer;


import com.codegym.model.person.employee.Accountant;
import com.codegym.model.person.employee.Employee;
import com.codegym.model.person.employee.Owner;
import com.codegym.model.person.enumerations.AgeCategory;
import com.codegym.model.person.enumerations.GenderPerson;
import com.codegym.model.person.visitors.Adult;
import com.codegym.model.person.visitors.Child;
import com.codegym.model.person.visitors.Senior;
import com.codegym.model.person.visitors.Visitor;
import com.codegym.model.ticket.AdultTicket;
import com.codegym.model.ticket.ChildTicket;
import com.codegym.model.ticket.SeniorTicket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadCustomerSerializer {

    private static ReadCustomerSerializer readCustomerSerializer;

    public static ReadCustomerSerializer getInstanceReadCustomerSerializer() {
        if (readCustomerSerializer == null) {
            readCustomerSerializer = new ReadCustomerSerializer();
        }
        return readCustomerSerializer;
    }


    private ReadCustomerSerializer() {
    }

    public void writeToCSV(List<Visitor> objects) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Zoo-system/visitor.csv"))) {
            writer.println("Visitor ID,Name,Address,Gender,Age,Age Category");
            for (Visitor obj : objects) {
                writer.println(obj.getVisitorID() + "," +
                        obj.getName() + "," +
                        obj.getStreetAddress() + "," +
                        obj.getGender() + "," +
                        obj.getAge() + "," +
                        obj.getAgeCategory()
                );

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Visitor> readFromCSV() {
        List<Visitor> loadedObjects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Zoo-system/visitor.csv"))) {
            String line;
            Boolean co = false;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && co) {
                    String visitorId = data[0];
                    String name = data[1];
                    String streetAddress = data[2];
                    GenderPerson gender = GenderPerson.valueOf(data[3]);
                    int age = Integer.valueOf(data[4]);
                    AgeCategory ageCategory = AgeCategory.valueOf(data[5]);
                    Visitor visitor;
                    if (age <= 12) {
                        visitor = new Child(visitorId,name, streetAddress, gender, age,  ageCategory, new ChildTicket());
                    } else if (age <= 64) {
                        visitor = new Adult(visitorId,name, streetAddress, gender, age,  ageCategory, new AdultTicket());
                    } else {
                        visitor = new Senior(visitorId,name, streetAddress, gender, age,  ageCategory, new SeniorTicket());
                    }

                    loadedObjects.add(visitor);
                }
                co = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadedObjects;
    }
}

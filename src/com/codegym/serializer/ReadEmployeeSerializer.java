package com.codegym.serializer;

import com.codegym.model.person.Person;
import com.codegym.model.person.employee.Accountant;
import com.codegym.model.person.employee.Employee;
import com.codegym.model.person.employee.Owner;
import com.codegym.model.person.employee.WarehouseManager;
import com.codegym.model.person.enumerations.GenderPerson;
import com.codegym.model.supply.Supply;
import com.codegym.model.supply.enumerations.BuildingMaterialType;
import com.codegym.model.supply.enumerations.FoodType;
import com.codegym.model.supply.enumerations.MedicineType;
import com.codegym.model.supply.types.BuildingMaterialSupply;
import com.codegym.model.supply.types.FoodSupply;
import com.codegym.model.supply.types.MedicineSupply;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadEmployeeSerializer {
    private static ReadEmployeeSerializer readEmployeeSerializer;

    public static ReadEmployeeSerializer getInstanceReadEmployeeSerializer() {
        if (readEmployeeSerializer == null) {
            readEmployeeSerializer = new ReadEmployeeSerializer();
        }
        return readEmployeeSerializer;
    }


    private ReadEmployeeSerializer() {
    }

    public void writeToCSV(List<Employee> objects) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Zoo-system/employee.csv"))) {
            writer.println("Employee Id,Name,Street, City,Country,Gender,Age,Job Position,Salary,Role, Username, Password, Total Money");
            for (Employee obj : objects) {
                if (obj instanceof Employee) {
                    writer.println(obj.getEmployeeId() + "," +
                            obj.getName() + "," +
                            obj.getStreetAddress() + "," +
                            obj.getCity() + "," +
                            obj.getCountry() + "," +
                            obj.getGender() + "," +
                            obj.getAge() + "," +
                            obj.getJobPosition() + "," +
                            obj.getSalary() + "," +
                            obj.getRole() + "," +
                            obj.getUsername()+ "," +
                            obj.getPassword()+ ","+ ((Employee) obj).getTotalMoney()
                    );
                }
                else {
                    writer.println( obj.getEmployeeId() + "," +
                            obj.getName() + "," +
                            obj.getStreetAddress() + "," +
                            obj.getCity() + "," +
                            obj.getCountry() + "," +
                            obj.getGender() + "," +
                            obj.getAge() + "," +
                            obj.getJobPosition() + "," +
                            obj.getSalary() + "," +
                            obj.getRole() + "," + " "+ "," +
                            obj.getUsername()+ "," +
                            obj.getPassword()+ ","+ ((Owner) obj).getTotalMoney()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Employee> readFromCSV() {
        List<Employee> loadedObjects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Zoo-system/employee.csv"))) {
            String line;
            Boolean co = false;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && co) {
                    String employeeId = data[0];
                    String name= data[1];
                    String streetAddress= data[2];
                    String city= data[3];
                    String country= data[4];
                    GenderPerson gender = GenderPerson.valueOf(data[5]);
                    int age = Integer.valueOf(data[6]);

                    String jobPosition = data[7];
                    double salary = Double.parseDouble(data[8]);
                    String role = data[9];

                    String username = data[10];
                    String password = data[11];
                    String totalMoneyString = data[12];
                    double totalMoney=0;
                    if (!totalMoneyString.isBlank()){
                        totalMoney = Double.valueOf(totalMoneyString);
                    }

                    Employee employee ;
                    if ("Owner".equals(role)){
                        employee = new Owner(name,streetAddress,city,country,gender,age,employeeId,jobPosition,salary,username,password,totalMoney);
                    }else if ("WarehouseManager".equals(role)){
                        employee =new WarehouseManager(name,streetAddress,city,country,gender,age,employeeId,jobPosition,salary,username,password,totalMoney);
                    }else  if ("Accountant".equals(role)){
                        employee =new Accountant(name,streetAddress,city,country,gender,age,employeeId,jobPosition,salary,username,password,totalMoney);
                    }else {
                        employee = new Employee(name,streetAddress,city,country,gender,age,employeeId,jobPosition,salary,"Zookeeper",username,password,totalMoney);
                    }
                    loadedObjects.add(employee);
                }
                co = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadedObjects;
    }
}

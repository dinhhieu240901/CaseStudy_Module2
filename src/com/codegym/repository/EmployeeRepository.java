package com.codegym.repository;

import com.codegym.model.person.employee.Employee;
import com.codegym.serializer.ReadEmployeeSerializer;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private final List<Employee> employees;
    ReadEmployeeSerializer readEmployeeSerializer=    ReadEmployeeSerializer.getInstanceReadEmployeeSerializer();
    TransactionHistoryRepository transactionHistoryRepository=    TransactionHistoryRepository.getHistoryTransactionRepository();

    private static EmployeeRepository employeeRepository;

    private EmployeeRepository() {
        this.employees = readEmployeeSerializer.readFromCSV();
    }

    public static EmployeeRepository getEmployeeRepository(){
        if (employeeRepository == null){
            employeeRepository = new EmployeeRepository();
        }
        return employeeRepository;
    }

    public void addEmployee(Employee employee) {
        boolean isIdExist = isEmployeeIdExist(employee.getEmployeeId());

        if (!isIdExist ) {
            employees.add(employee);
            updateFileCSV();
            System.out.println("Thêm nhân viên thành công.");
        } else {
                System.out.println("Mã nhân viên " + employee.getEmployeeId() + " đã tồn tại.");
        }
    }

    private boolean isEmployeeIdExist(String employeeId) {
        for (Employee existingEmployee : employees) {
            if (existingEmployee.getEmployeeId().equals(employeeId)) {
                return true;
            }
        }
        return false;
    }


    public void removeEmployee(String employeeId) {
        employees.removeIf(employee -> employee.getEmployeeId().equals(employeeId));
        updateFileCSV();
    }

    public Employee findEmployeeById(String employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }


    public Employee findEmployeeByUsernameAndPassword(String username, String password) {
        for (Employee employee : employees) {
            if (username.equals(employee.getUsername()) && password.equals(employee.getPassword())) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> findEmployeesByName(String name) {
        List<Employee> matchingEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                matchingEmployees.add(employee);
            }
        }
        return matchingEmployees;
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public void updateEmployee(Employee updatedEmployee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId().equals(updatedEmployee.getEmployeeId())) {
                employees.set(i, updatedEmployee);
                updateFileCSV();
                return;
            }
        }
    }

    public void updateFileCSV(){
        readEmployeeSerializer.writeToCSV(employees);
    }
}

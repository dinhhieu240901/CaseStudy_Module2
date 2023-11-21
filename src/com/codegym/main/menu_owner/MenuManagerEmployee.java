package com.codegym.main.menu_owner;

import com.codegym.model.person.employee.Accountant;
import com.codegym.model.person.employee.Employee;
import com.codegym.model.person.employee.Owner;
import com.codegym.model.person.employee.WarehouseManager;
import com.codegym.model.person.enumerations.GenderPerson;
import com.codegym.service.EmployeeService;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuManagerEmployee {
    EmployeeService employeeService = EmployeeService.getEmployeeService();

    public void menuManagerEmployee() {

        while (true) {
            System.out.println("=== Quản lý thông tin nhân viên ===");
            System.out.println("1. Danh sách thông tin nhân viên");
            System.out.println("2. Thêm thông tin nhân viên");
            System.out.println("3. Sửa thông tin nhân viên");
            System.out.println("4. Xoá thông tin nhân viên");
            System.out.println("5. Tìm kiếm thông tin nhân viên");
            System.out.println("6. Thanh toán lương cho nhân viên");
            System.out.println("7. Quay lại menu");
            Scanner scanner = new Scanner(System.in);
            int inputMenuAnimalSelected = scanner.nextInt();
            switch (inputMenuAnimalSelected) {
                case 1:
                    for (Employee employee : employeeService.getEmployees()) {
                        System.out.println(employee);
                    }
                    break;
                case 2:
                    employeeService.addEmployee(getNewEmployee());
                    break;
                case 3:
                    employeeService.updateEmployee(getNewEmployee());
                    System.out.println("Update nhân viên thành công.");
                    break;
                case 4:
                    System.out.println("Id nhân viên muốn xoá: ");
                    String inputAnimalDeleted = scanner.next();
                    employeeService.removeEmployee(inputAnimalDeleted);
                    System.out.println("Xoá nhân viên thành công.");
                    break;
                case 5:
                    System.out.println("Nhập tên nhân viên muốn tìm kiếm ");
                    String inputNameEmployee = scanner.next();
                    employeeService.findByName(inputNameEmployee);
                    for (Employee employee : employeeService.findByName(inputNameEmployee)) {
                        System.out.println(employee);
                    }
                    break;
                case 6:
                    employeeService.payWage();
                case 7:
                    return;
            }
        }
    }

    public Employee getNewEmployee() {
        Employee employee = null;
        Scanner scanner = new Scanner(System.in);

        int inputEmployeeType;
        do {
            System.out.println("=== Chọn nhân viên ===");
            System.out.println("1. Zookeeper");
            System.out.println("2. Accountant");
            System.out.println("3. Owner");
            System.out.println("4. WarehouseManager");
            inputEmployeeType = scanner.nextInt();

            if (inputEmployeeType < 1 || inputEmployeeType > 4) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (inputEmployeeType < 1 || inputEmployeeType > 4);

        System.out.println("Employee Id:");
        String employeeId = scanner.next();
        System.out.println("Job Position:");
        String jobPosition = scanner.next();
        System.out.println("Salary:");
        double salary = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Street Address:");
        String streetAddress = scanner.nextLine();
        System.out.println("City:");
        String city = scanner.nextLine();
        System.out.println("Country:");
        String country = scanner.nextLine();
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        System.out.println("Age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Gender Person MALE, FEMALE, OTHER:");
        GenderPerson gender = GenderPerson.valueOf(scanner.next());
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,20}$");
        Matcher matcher = pattern.matcher(username);
        Pattern patternPass = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,}$");
        Matcher matcherPass  = patternPass.matcher(password);
        boolean matchFound = matcher.find();
        boolean matchFoundPass = matcherPass.find();
        if (!matchFound ||  !matchFoundPass) {
            System.out.println("Username hoặc Password không hợp lệ.");
            return getNewEmployee();
        }
        switch (inputEmployeeType) {
            case 1:
                employee = new Employee(name, streetAddress, city, country, gender, age, employeeId, jobPosition, salary, "Zookeeper", username, password, 0);
                break;
            case 2:
                employee = new Accountant(name, streetAddress, city, country, gender, age, employeeId, jobPosition, salary, username, password, 0);
                break;
            case 3:
                employee = new Owner(name, streetAddress, city, country, gender, age, employeeId, jobPosition, salary, username, password, 0);
                break;
            case 4:
                employee = new WarehouseManager(name, streetAddress, city, country, gender, age, employeeId, jobPosition, salary, username, password, 0);
                break;
        }
        return employee;

    }
}



package com.codegym.main.menu_owner;

import com.codegym.model.person.enumerations.AgeCategory;
import com.codegym.model.person.enumerations.GenderPerson;
import com.codegym.model.person.visitors.Adult;
import com.codegym.model.person.visitors.Child;
import com.codegym.model.person.visitors.Senior;
import com.codegym.model.person.visitors.Visitor;
import com.codegym.model.ticket.AdultTicket;
import com.codegym.model.ticket.ChildTicket;
import com.codegym.model.ticket.SeniorTicket;
import com.codegym.repository.VisitorRepository;
import com.codegym.service.VisitorService;

import java.util.List;
import java.util.Scanner;

import static com.codegym.repository.VisitorRepository.findVisitorById;

public class MenuVisitorManagement {
    private VisitorService visitorService = VisitorService.getVisitorService();
    private VisitorRepository visitorRepository = VisitorRepository.getVisitorRepository();

    public void menuVisitorManagement() {
        while (true) {
            System.out.println("=== MENU QUẢN LÝ KHÁCH THĂM QUAN ===");
            System.out.println("1. Xem danh sách khách thăm quan");
            System.out.println("2. Thêm khách thăm quan");
            System.out.println("3. Sửa thông tin khách thăm quan");
            System.out.println("4. Xóa khách thăm quan");
            System.out.println("5. Tìm kiếm khách thăm quan theo độ tuổi");
            System.out.println("6. Sắp xếp khách thăm quan theo tên");
            System.out.println("7. Quay lại Menu chính");
            Scanner scanner = new Scanner(System.in);
            int inputMenuVisitorSelected = scanner.nextInt();

            switch (inputMenuVisitorSelected) {
                case 1:
                    displayAllVisitors();
                    break;
                case 2:
                    addVisitor();
                    break;
                case 3:
                    updateVisitor();
                    break;
                case 4:
                    removeVisitor();
                    break;
                case 5:
                    searchVisitorsByAge();
                    break;
                case 6:
                    sortVisitorsByName();
                    break;
                case 7:
                    return;
            }
        }
    }

    private void addVisitor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã số khách thăm quan:");
        String visitorID = scanner.next();
        scanner.nextLine();
        System.out.println("Nhập tên khách thăm quan:");
        String name = scanner.nextLine();

        System.out.println("Nhập địa chỉ:");
        String streetAddress = scanner.nextLine();
        System.out.println("Chọn giới tính (1: Nam, 2: Nữ, 3: Khác):");
        int genderChoice = scanner.nextInt();
        GenderPerson gender;
        switch (genderChoice) {
            case 1:
                gender = GenderPerson.MALE;
                break;
            case 2:
                gender = GenderPerson.FEMALE;
                break;
            case 3:
                gender = GenderPerson.OTHER;
                break;
            default:
                gender = null;
                break;
        }
        System.out.println("Nhập tuổi:");
        int age = scanner.nextInt();
        scanner.nextLine();
        AgeCategory ageCategory;
        if (age <= 12) {
            ageCategory = AgeCategory.CHILD;
        } else if (age <= 64) {
            ageCategory = AgeCategory.ADULT;
        } else {
            ageCategory = AgeCategory.SENIOR;
        }

        System.out.println("Chọn loại vé (1: Trẻ em, 2: Người lớn, 3: Người cao tuổi):");
        int ticketChoice = scanner.nextInt();
        scanner.nextLine();
        Visitor visitor;
        switch (ticketChoice) {
            case 1:
                visitor = new Child(visitorID, name, streetAddress, gender, age, ageCategory, new ChildTicket());
                break;
            case 2:
                visitor = new Adult(visitorID, name, streetAddress, gender, age, ageCategory, new AdultTicket());
                break;
            case 3:
                visitor = new Senior(visitorID, name, streetAddress, gender, age, ageCategory, new SeniorTicket());
                break;
            default:
                visitor = null;
                break;
        }

        visitorService.addVisitor(visitor);

        System.out.println("Đã thêm khách thăm quan thành công.");
    }


    private void updateVisitor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập mã số khách thăm quan cần sửa:");
        String visitorID = scanner.next();
        scanner.nextLine();
        Visitor existingVisitor = findVisitorById(visitorID);
        if (existingVisitor == null) {
            System.out.println("Không tìm thấy khách thăm quan với mã số " + visitorID);
            return;
        }
        System.out.println("Nhập tên mới (nhập 'skip' nếu không muốn sửa):");
        String newName = scanner.nextLine();
        if (newName.equals("skip")) {
            newName = existingVisitor.getName();
        }

        System.out.println("Nhập địa chỉ mới (nhập 'skip' nếu không muốn sửa):");
        String newAddress = scanner.nextLine();
        if (newAddress.equals("skip")) {
            newAddress = existingVisitor.getStreetAddress();
        }


        System.out.println("Chọn giới tính (1: Nam, 2: Nữ, 3: Khác):");
        int genderChoice = scanner.nextInt();
        GenderPerson gender;
        switch (genderChoice) {
            case 1:
                gender = GenderPerson.MALE;
                break;
            case 2:
                gender = GenderPerson.FEMALE;
                break;
            case 3:
                gender = GenderPerson.OTHER;
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Mặc định sẽ là OTHER.");
                gender = GenderPerson.OTHER;
                break;
        }

        System.out.println("Nhập tuổi mới (nhập -1 nếu không muốn sửa) :");
        int newAge = scanner.nextInt();
        if (newAge == -1) {
            newAge = existingVisitor.getAge();
        }

        System.out.println("Nhập mã số khách thăm quan mới (nhập 'skip' nếu không muốn sửa):");
        String newVisitorID = scanner.next();
        if (newVisitorID.equals("skip")) {
            newVisitorID = existingVisitor.getVisitorID();
        }

        AgeCategory ageCategory;
        if (newAge <= 12) {
            ageCategory = AgeCategory.CHILD;
        } else if (newAge <= 64) {
            ageCategory = AgeCategory.ADULT;
        } else {
            ageCategory = AgeCategory.SENIOR;
        }

        Visitor updatedVisitor = new Visitor(
                newVisitorID,
                newName,
                newAddress,
                gender,
                newAge,
                ageCategory
        );

        visitorService.updateVisitor(updatedVisitor);
        System.out.println("Đã cập nhật thông tin khách thăm quan thành công.");
    }

    private void displayAllVisitors() {
        List<Visitor> visitors = visitorService.getVisitors();
        for (Visitor visitor : visitors) {
            System.out.println(visitor);
        }
    }

    private void removeVisitor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập mã số khách thăm quan cần xoá:");
        String visitorID = scanner.next();

        Visitor existingVisitor = findVisitorById(visitorID);
        if (existingVisitor == null) {
            System.out.println("Không tìm thấy khách thăm quan với mã số " + visitorID);
            return;
        }
        visitorService.removeVisitor(visitorID);

        System.out.println("Đã xoá khách thăm quan thành công.");
    }

    private void searchVisitorsByAge() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập loại độ tuổi cần tìm (CHILD, ADULT, SENIOR):");
        AgeCategory ageCategory = AgeCategory.valueOf(scanner.next());
        List<Visitor> matchingVisitors = visitorService.findById(ageCategory);
        if (matchingVisitors.isEmpty()) {
            System.out.println("Không có khách thăm quan nào thuộc độ tuổi " + ageCategory);
        } else {
            System.out.println("Danh sách khách thăm quan thuộc độ tuổi " + ageCategory + ":");
            for (Visitor visitor : matchingVisitors) {
                System.out.println(visitor);
            }
        }
    }

    private void sortVisitorsByName() {
        visitorService.sortVisitors();
        System.out.println("Đã sắp xếp danh sách khách thăm quan theo tên.");
        displayAllVisitors();
    }
}

package com.codegym.main.menu_owner;

import com.codegym.model.Cage.AquaticHabitat;
import com.codegym.model.Cage.Aviary;
import com.codegym.model.Cage.Cage;
import com.codegym.model.Cage.TerrestrialHabitat;
import com.codegym.service.CageService;

import java.util.Scanner;

public class MenuManagerCage {
    Scanner scanner = new Scanner(System.in);

    CageService cageService = CageService.getCageService();

    public void menuAnimalCage() {
        while (true) {
            displayCageMenu();
            Scanner scanner = new Scanner(System.in);
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1:
                    displayCagesInfo();
                    break;
                case 2:
                    addCage();
                    break;
                case 3:
                    updateCage();
                    break;
                case 4:
                    addAnimalToCage();
                    break;
                case 5:
                    searchAnimalsByID();
                    break;
                case 6:
                    removeAnimalFromCage();
                    break;
                case 7:
                    removeCage();
                    break;
                case 8:
                    System.out.println("7. Thoát về Menu chính");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
    private void displayCageMenu() {
        System.out.println("=== MENU QUẢN CHUỒNG THÚ ===");
        System.out.println("1. Xem thông tin chuồng thú.");
        System.out.println("2. Thêm thông tin chuồng thú.");
        System.out.println("3. Sửa thông tin chuồng thú.");
        System.out.println("4. Thêm thú vào chuồng.");
        System.out.println("5. Tìm kiếm chuồng thú theo id");
        System.out.println("6. Xoá thú ra khỏi chuồng.");
        System.out.println("7. Xoá chuồng.");
        System.out.println("8. Quay lại menu");
    }
    private void displayCagesInfo() {
        for (Cage cage : cageService.getCages()) {
            System.out.println(cage);
        }
    }
    private void addCage() {
        Cage newCage = getNewCage();
        if (newCage != null) {
            cageService.addCage(newCage);
            System.out.println("Thêm chuồng thú thành Công.");
        } else {
            System.out.println("Không thể tạo chuồng.");
        }
    }
    public Cage getNewCage() {
        Cage cage = null;
        int inputAnimalSelected;
        do {
            System.out.println("=== Chọn loại chuồng ===");
            System.out.println("1. Aquatic Habitat");
            System.out.println("2. Aviary");
            System.out.println("3. Terrestrial Habitat");
            inputAnimalSelected = scanner.nextInt();
            if (inputAnimalSelected < 1 || inputAnimalSelected > 3) {
                System.out.println("Bạn đã nhập một số không hợp lệ. Vui lòng thử lại.");
            }
        } while (inputAnimalSelected < 1 || inputAnimalSelected > 3);

        System.out.println("Id: ");
        String id = scanner.next();
        scanner.nextLine();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Max Animals: ");
        int maxAnimals = scanner.nextInt();
        System.out.println("Employee Id: ");
        String employeeId = scanner.next();
        switch (inputAnimalSelected) {
            case 1:
                System.out.println("Deepness: ");
                int deepness = scanner.nextInt();
                if (deepness > 0) {
                    cage = new AquaticHabitat(id, name, maxAnimals, deepness, employeeId);
                } else {
                    System.out.println("Deepness must be greater than 0 for Aquatic Habitat.");
                }
                break;
            case 2:
                System.out.println("Height: ");
                int heightAviary = scanner.nextInt();
                if (heightAviary > 0) {
                    cage = new Aviary(id, name, maxAnimals, heightAviary, employeeId);
                } else {
                    System.out.println("Height must be greater than 0 for Aviary.");
                }
                break;
            case 3:
                System.out.println("Height: ");
                int heightTerrestrialHabitat = scanner.nextInt();
                if (heightTerrestrialHabitat > 0) {
                    cage = new TerrestrialHabitat(id, name, maxAnimals, heightTerrestrialHabitat, employeeId);
                } else {
                    System.out.println("Height must be greater than 0 for Terrestrial Habitat.");
                }
                break;
        }
        return cage;
    }


    private void updateCage(){
        System.out.println("Nhập ID chuồng bạn muốn cập nhật:");
        String cageId = scanner.next();
        Cage existingCage = cageService.findById(cageId);
        if (existingCage != null) {
            Cage updatedCage = getNewCage();
            if (updatedCage != null) {
                updatedCage.setCageId(cageId);
                cageService.updateCage(updatedCage);
                System.out.println("Cập nhật thành công.");
            } else {
                System.out.println("Không thể cập nhật chuồng.");
            }
        } else {
            System.out.println("Không tìm thấy chuồng với ID đã cho.");
        }
    }
    private void addAnimalToCage(){
        System.out.println("Nhập ID chuồng muốn thêm thú: ");
        String cageId = scanner.next();
        System.out.println("Nhập thú id: ");
        int animalId = scanner.nextInt();
        cageService.addAnimalForCage(cageId, animalId);
    }
    private void removeCage(){
        System.out.println("Id chuồng muốn xoá: ");
        String inputCageDeleted = scanner.next();
        cageService.removeCage(inputCageDeleted);
        System.out.println("Xoá chuồng thành công.");
    }
    private void removeAnimalFromCage(){
        System.out.println("Nhập ID chuồng muốn xoá thú: ");
        String cageId = scanner.next();
        System.out.println("Nhập ID thú: ");
        int animalId = scanner.nextInt();
        cageService.removeAnimalForCage(cageId, animalId);
    }
    private void searchAnimalsByID(){
        System.out.println("Nhập ID chuồng muốn tìm: ");
        String cageId = scanner.next();
        Cage foundCage = cageService.findById(cageId);
        if (foundCage == null) {
            System.out.println("Không tìm thấy chuồng với ID đã nhập.");
        } else {
            System.out.println(foundCage);
        }
    }

}

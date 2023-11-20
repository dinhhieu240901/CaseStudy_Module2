package com.codegym.main.menu_owner;

import com.codegym.model.animal.Animal;
import com.codegym.model.animal.enumerations.GenderAnimals;
import com.codegym.model.animal.enumerations.HealAnimals;
import com.codegym.model.animal.species.*;
import com.codegym.model.foodplan.FoodPlan;
import com.codegym.service.AnimalService;

import java.util.List;
import java.util.Scanner;

public class MenuManagerAnimal {
    AnimalService animalService = AnimalService.getAnimalService();
    Scanner scanner = new Scanner(System.in);


    public void menuManagerAnimal() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("=== MENU QUẢN LÝ THÚ ===");
            System.out.println("1. Thêm/Chỉnh sửa thông tin thú");
            System.out.println("2. Duy trì chế độ ăn uống");
            System.out.println("3. Theo dõi sức khỏe");
            System.out.println("4. Thoát về Menu chính");
            int inputMenuManagerAnimalSelected = scanner.nextInt();
            switch (inputMenuManagerAnimalSelected) {
                case 1:
                    menuAnimal();
                    break;
                case 2:
                    manageFoodPlan();
                    break;
                case 3:
                    trackHealth();
                    break;
                case 4:
                    System.out.println("4. Thoát về Menu chính");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
    }

    public void menuAnimal() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("=== Thêm/Chỉnh sửa thông tin thú ===");
            System.out.println("1.Danh sách thông tin thú");
            System.out.println("2.Thêm thông tin thú");
            System.out.println("3.Sửa thông tin thú");
            System.out.println("4.Xoá thông tin thú");
            System.out.println("5.Sắp xếp danh sách thú theo tên");
            System.out.println("6.Tìm kiếm thú theo tên");
            System.out.println("7.Thoát về Menu chính");
            Scanner scanner = new Scanner(System.in);
            int inputMenuAnimalSelected = scanner.nextInt();
            switch (inputMenuAnimalSelected) {
                case 1:
                    for (Animal animal : animalService.getAnimals()) {
                        System.out.println(animal);
                    }
                    break;
                case 2:
                    animalService.addAnimal(getNewAnimal());
                    System.out.println("Thêm thú thành công.");
                    break;
                case 3:
                    animalService.updateAnimal(getNewAnimal());
                    System.out.println("Update thú thành công.");
                    break;
                case 4:
                    System.out.println("Id thú muốn xoá: ");
                    int inputAnimalDeleted = scanner.nextInt();
                    animalService.removeAnimal(inputAnimalDeleted);
                    System.out.println("Xoá thú thành công.");
                    break;
                case 5:
                    animalService.sortAnimalByName();
                    System.out.println("Sắp xếp danh sách thú theo tên thành công.");
                    break;
                case 6:
                    System.out.println("Nhập tên thú muốn tìm: ");
                    String inputAnimalName = scanner.next();
                    List<Animal> foundAnimals = animalService.findByName(inputAnimalName);
                    if (foundAnimals.isEmpty()) {
                        System.out.println("Không tìm thấy thú với tên đã nhập.");
                    } else {
                        for (Animal animal : foundAnimals) {
                            System.out.println(animal);
                        }
                    }
                    break;
                case 7:
                    System.out.println("7. Thoát về Menu chính");
                    isRunning = false;
                    break;
            }
        }
    }
    private Animal getNewAnimal() {
        Animal animal = null;
        System.out.println("=== Chọn loại thú ===");
        System.out.println("1.Dolphin");
        System.out.println("2.Eagle");
        System.out.println("3.Elephant");
        System.out.println("4.Lion");
        System.out.println("5.Panda");
        System.out.println("6.Seal");
        System.out.println("7.Shark");
        System.out.println("8.Zebra");
        Scanner scanner = new Scanner(System.in);
        int inputAnimalSelected = scanner.nextInt();
        System.out.println("Id:");
        int id = scanner.nextInt();
        System.out.println("Name:");
        String name = scanner.next();
        System.out.println("Species:");
        String species = scanner.next();
        System.out.println("Weight:");
        double weight = scanner.nextDouble();
        System.out.println("Size:");
        double size = scanner.nextDouble();
        ;
        System.out.println("Age:");
        int age = scanner.nextInt();
        System.out.println("Heal Animals in GOOD,\n" +
                "    INJURED,\n" +
                "    SICK;:");
        HealAnimals heal = HealAnimals.valueOf(scanner.next());
        System.out.println("Gender Animals in      MALE,\n" +
                "    FEMALE :");
        GenderAnimals gender = GenderAnimals.valueOf(scanner.next());
        switch (inputAnimalSelected) {
            case 1:
                animal = new Dolphin(id, name, species, weight, size, age, heal, gender, null, null);
                break;
            case 2:
                animal = new Eagle(id, name, species, weight, size, age, heal, gender, null, null);
                break;
            case 3:
                animal = new Elephant(id, name, species, weight, size, age, heal, gender, null, null);
                break;
            case 4:
                animal = new Lion(id, name, species, weight, size, age, heal, gender, null, null);
                break;
            case 5:
                animal = new Panda(id, name, species, weight, size, age, heal, gender, null, null);
                break;
            case 6:
                animal = new Seal(id, name, species, weight, size, age, heal, gender, null, null);
                break;
            case 7:
                animal = new Shark(id, name, species, weight, size, age, heal, gender, null, null);
                break;
            case 8:
                animal = new Zebra(id, name, species, weight, size, age, heal, gender, null, null);
                break;
        }
        return animal;
    }
    private void manageFoodPlan(){
        for (Animal animal : animalService.getAnimals()) {
            System.out.println(animal);
        }
        System.out.println("Nhập thú id: ");
        int animalId = scanner.nextInt();
        System.out.println("Nhập tên thực phẩm: ");
        String foodItem = scanner.next();
        System.out.println("Nhập số lượng");
        int quantity = scanner.nextInt();

        animalService.addFoodPlan(animalId,new FoodPlan(foodItem,quantity));
        System.out.println("Đã cập nhật kế hoạch ăn cho thú thành công.");
    }
    private void trackHealth() {
        for (Animal animal : animalService.getAnimals()) {
            System.out.println(animal);
        }
        System.out.println("Nhập thú id: ");
        int animalId = scanner.nextInt();
        HealAnimals healthStatus = animalService.getHealthStatus(animalId);
        System.out.println("Thông tin sức khỏe cho thú có id " + animalId + ":");
        System.out.println(healthStatus);
    }
}

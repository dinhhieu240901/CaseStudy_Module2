package com.codegym.service;

import com.codegym.model.Cage.Cage;
import com.codegym.model.animal.Animal;
import com.codegym.model.supply.Warehouse;
import com.codegym.repository.AnimalRepository;
import com.codegym.repository.CageRepository;

import java.util.List;
import java.util.Map;

public class CageService extends BaseService{


    private static CageService cageService;

    private CageService() {
        this.warehouse = new Warehouse();
    }

    public static CageService getCageService() {
        if (cageService == null) {
            cageService = new CageService();
        }
        return cageService;
    }


    private CageRepository cageRepository = CageRepository.getCageRepository();

    private AnimalRepository animalRepository = AnimalRepository.getAnimalRepository();
    private Warehouse warehouse = new Warehouse();


    public List<Cage> getCages() {
        return cageRepository.getAllCages();
    }

    public List<Cage> getCagesForUser() {
        return cageRepository.getAllCagesForUserId(getEmployeeId());
    }


    public void feedAnimals(String cageId, int foodQuantity){
        Cage cage = cageRepository.findById(cageId);
        if (cage == null){
            System.out.println("Thông tin chuồng thú không hợp lệ.");
        }else if (cage.isEmpty()){
            System.out.println("Chuồng phải chứa động vật.");
        }else {
            System.out.println("Nhân viên đang cho thú ăn.");
            List<Animal> animals = cage.getAnimals();
            for (Animal animal : animals) {
                int foodSupplyId = animal.getFoodSupplyId();
                int currentQuantity = warehouse.getSupplyQuantity(foodSupplyId);
                Map<Integer, String> supplyData = warehouse.getSupplyData();
                String foodSupplyName = supplyData.get(foodSupplyId);
                System.out.println(currentQuantity);
                if (currentQuantity < 10) {
                    System.out.println("Nguyên liệu " + foodSupplyName + " với ID " + foodSupplyId + " trong kho không đủ.");
                    break;
                } else if (currentQuantity >= foodQuantity) {
                    warehouse.addSupply(foodSupplyId, -foodQuantity);
                    animal.eat();
                    animal.setHungry(false);
                    System.out.println(animal.getName() + " đã ăn " + foodQuantity + " đơn vị thức ăn.");
                } else {
                    System.out.println("Không đủ thức ăn trong kho để cho " + animal.getName() + " ăn.");
                }
            }
        }
    }





    public void cleanEnclosure(String cageId){
        Cage cage = cageRepository.findById(cageId);
        if (cage == null){
            System.out.println("Thông tin chuồng thú không hợp lệ.");
        }else if (cage.isEmpty()){
            System.out.println("Chuồng phải chứa động vật.");
        }else {
            System.out.println("Chuồng đã được vệ sinh");
            cage.getAnimals().parallelStream().forEach(x -> {
                x.move();
                x.makeSound();
                x.play();
            });
        }
    }

    public void addAnimalForCage(String cageId, int animalId) {
        Cage cage = cageRepository.findById(cageId);
        Animal animal = animalRepository.findById(animalId);
        if (cage == null || animal == null) {
            System.out.println("Thông tin chuồng hoặc thú không hợp lệ.");
        } else if (cage.isFull()) {
            System.out.println("Chuồng thú đã đầy.");
        } else if (!cage.isCompatible(animal)) {
            System.out.println("Chuồng không đúng loại thú.");
        } else {
//            if (!animal.getCageId().isBlank()) {
//                Cage oldCage = cageRepository.findById(animal.getCageId());
//                oldCage.getAnimals().removeIf(x -> x.getId()== animalId);
//            }
            animal.setCageId(cageId);
            cage.getAnimals().add(animal);
            animalRepository.updateFileCSV();

            System.out.println("Thành Công.");
        }
    }


    public void removeAnimalForCage(String cageId, int animalId) {
        Cage cage = cageRepository.findById(cageId);
        Animal animal = animalRepository.findById(animalId);
        if (cage == null || animal == null) {
            System.out.println("Thông tin chuồng hoặc thú không hợp lệ.");

        } else {
            cage.getAnimals().remove(animal);
            animal.setCageId(null);
            animalRepository.updateFileCSV();
            System.out.println("Thành Công.");
        }

    }

    public Cage findById(String cageId) {
        return cageRepository.findById(cageId);
    }

    public void addCage(Cage cage) {
        cageRepository.addCage(cage);
    }

    public void updateCage(Cage cage) {
        cageRepository.updateCage(cage);
    }

    public void removeCage(String cageId) {
        cageRepository.removeCage(cageId);
    }
}

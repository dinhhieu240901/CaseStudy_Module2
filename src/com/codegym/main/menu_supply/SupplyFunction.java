package com.codegym.main.menu_supply;

import com.codegym.main.Command.Command;
import com.codegym.model.supply.Supply;
import com.codegym.model.supply.enumerations.BuildingMaterialType;
import com.codegym.model.supply.enumerations.FoodType;
import com.codegym.model.supply.enumerations.MedicineType;
import com.codegym.model.supply.types.BuildingMaterialSupply;
import com.codegym.model.supply.types.FoodSupply;
import com.codegym.model.supply.types.MedicineSupply;
import com.codegym.service.HistoryRequestBuySupplyService;
import com.codegym.service.SupplyService;

import java.util.Scanner;

public class SupplyFunction implements Command {
    SupplyService supplyService = SupplyService.getSupplyService();
    HistoryRequestBuySupplyService historyRequestBuySupplyService = HistoryRequestBuySupplyService.getHistoryRequestBuySupplyService();
    @Override
    public void execute() {
        while (true) {
            System.out.println("=== MENU NHÂN VIÊN CƠ SỞ VÂT CHẤT ===");
            System.out.println("1. Xem danh sách các nguyên vật liệu.");
            System.out.println("2. Thêm các nguyên vật liệu.");
            System.out.println("3. Sửa các nguyên vật liệu.");
            System.out.println("4. Xoá các nguyên vật liệu.");
            System.out.println("5. Trở về Menu chính.");
            Scanner scanner = new Scanner(System.in);
            int inputMenuManagerCustomerSelected = scanner.nextInt();
            switch (inputMenuManagerCustomerSelected) {
                case 1:
                    supplyService.getSupplies().forEach(System.out::println);
                    break;
                case 2:
                    supplyService.addSupply(getSupply());
                    System.out.println("Thành Công");
                    break;
                case 3:
                    supplyService.updateSupply(getSupply());
                    System.out.println("Thành Công");
                    break;
                case 4:
                    System.out.println("Id muốn xoá: ");
                    int id = scanner.nextInt();
                    supplyService.removeSupply(id);
                    System.out.println("Thành Công");
                    break;
                case 5:
                    return;
            }
        }

    }
    public Supply getSupply() {
        Supply supply = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Thông tin vật liệu===");
        System.out.println("1. BuildingMaterialSupply ");
        System.out.println("2. FoodSupply ");
        System.out.println("3. MedicineSupply");
        System.out.println("Chọn loai vật liệu: ");
        int inputVisitorSelected = scanner.nextInt();
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("PricePerUnit: ");
        double pricePerUnit = scanner.nextDouble();
        System.out.println("QuantityAvailable: ");
        int quantityAvailable = scanner.nextInt();

        switch (inputVisitorSelected) {
            case 1:
                System.out.println("BuildingMaterialType WOOD,METAL,CONCRETE: ");
                BuildingMaterialType buildingMaterialType = BuildingMaterialType.valueOf(scanner.next());
                supply = new BuildingMaterialSupply(id,name,pricePerUnit,quantityAvailable,buildingMaterialType);
                break;
            case 2:
                System.out.println("FoodType  MEAT,FISH,VEGETABLES,INSECTS,FRUITS: ");
                FoodType foodType = FoodType.valueOf(scanner.next());
                supply = new FoodSupply(id,name,pricePerUnit,quantityAvailable,foodType);
                break;
            case 3:
                System.out.println("FoodType ANTIBIOTICS,VITAMIN,ANALGESIC,ANTIPARASITIC: ");
                MedicineType medicineType = MedicineType.valueOf(scanner.next());
                supply = new MedicineSupply(id,name,pricePerUnit,quantityAvailable,medicineType);
                break;
            default:
                break;
        }
        return supply;
    }
}

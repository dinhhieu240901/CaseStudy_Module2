package com.codegym.main.menu_owner;

import com.codegym.main.Command.Command;
import com.codegym.model.history.HistoryRequestBuySupply;
import com.codegym.model.supply.Supply;
import com.codegym.service.HistoryRequestBuySupplyService;
import com.codegym.service.SupplyService;

import java.util.List;
import java.util.Scanner;

public class OwnerFunction implements Command {
    private final MenuManagerEmployee menuManagerEmployee;
    private final MenuVisitorManagement menuVisitorManagement;
    private final MenuManagerAnimal menuManagerAnimal;
    private final MenuManagerCage menuManagerCage;
    private final SupplyService supplyService = SupplyService.getSupplyService();
    private final HistoryRequestBuySupplyService historyRequestBuySupplyService = HistoryRequestBuySupplyService.getHistoryRequestBuySupplyService();


    public OwnerFunction() {
        this.menuManagerEmployee = new MenuManagerEmployee();
        this.menuVisitorManagement = new MenuVisitorManagement();
        this.menuManagerAnimal = new MenuManagerAnimal();
        this.menuManagerCage = new MenuManagerCage();
    }

    @Override
    public void execute() {
        while (true) {
            System.out.println("=== MENU QUẢN LÝ OWNER ===");
            System.out.println("1. Quản lý khách thăm quan");
            System.out.println("2. Quản lý Nhân viên");
            System.out.println("3. Quản lý Thú");
            System.out.println("4. Quản lý chuồng thú");
            System.out.println("5. Quản lý nguyên liệu cung cấp");
            System.out.println("6.Thoát về Menu chính");
            Scanner scanner = new Scanner(System.in);
            int inputManagerOwnerSelected = scanner.nextInt();
            switch (inputManagerOwnerSelected) {
                case 1:
                    menuVisitorManagement.menuVisitorManagement();
                    break;
                case 2:
                    menuManagerEmployee.menuManagerEmployee();
                    break;
                case 3:
                    menuManagerAnimal.menuManagerAnimal();
                    break;
                case 4:
                    menuManagerCage.menuAnimalCage();
                    break;
                case 5:
                    menuManagerFacility();
                    break;
                case 6:
                    return;
            }
        }
    }

    public void menuManagerFacility() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("=== Quản lý thông tin nhân viên ===");
            System.out.println("1. Xem lịch sử yêu cầu mua nguyên liệu");
            System.out.println("2. Phê duyệt yêu cầu mua nguyên liệu");
            System.out.println("3. Mua nguyên liệu");
            System.out.println("4. Huỷ yêu cầu mua nguyên liệu");
            System.out.println("4. Thoát về Menu chính");

            try {
                int inputMenuAnimalSelected = scanner.nextInt();

                switch (inputMenuAnimalSelected) {
                    case 1:
                        List<HistoryRequestBuySupply> supplies = historyRequestBuySupplyService.getSupplies();
                        if (!supplies.isEmpty()) {
                            supplies.forEach(System.out::println);
                        } else {
                            System.out.println("Danh sách yêu cầu mua nguyên liệu trống.");
                        }
                        break;
                    case 2:
                        System.out.println("Nhập ID muốn phê duyệt:");
                        historyRequestBuySupplyService.updateSuccessHistoryRequestBuySupply(scanner.next());
                        break;
                    case 3:
                        purchaseSupply();
                        break;
                    case 4:

                        System.out.println("Nhập ID muốn huỷ duyệt:");
                        historyRequestBuySupplyService.updateFailHistoryRequestBuySupply(scanner.next());
                        break;
                    case 5:
                        System.out.println("Thoát về Menu chính");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại.");
                        break;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);

    }

    private void purchaseSupply() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ID nguyên liệu muốn mua:");
        int supplyId = scanner.nextInt();

        System.out.println("Nhập số lượng muốn mua:");
        int quantity = scanner.nextInt();

        Supply supply = supplyService.findById(supplyId);

        if (supply != null) {
            System.out.println("Thông tin nguyên liệu bạn muốn mua:");
            System.out.println(supply);

            System.out.println("Xác nhận mua? (Y/N)");
            String confirm = scanner.next();

            if (confirm.equalsIgnoreCase("Y")) {
                historyRequestBuySupplyService.purchaseSupply(supplyId, quantity);
            } else {
                System.out.println("Đã huỷ giao dịch mua nguyên liệu.");
            }
        } else {
            System.out.println("Không tìm thấy nguyên liệu với ID đã nhập.");
        }
    }
}

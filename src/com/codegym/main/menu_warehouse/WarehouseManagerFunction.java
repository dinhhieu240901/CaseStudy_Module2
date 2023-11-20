package com.codegym.main.menu_warehouse;

import com.codegym.main.Command.Command;
import com.codegym.model.supply.Supply;
import com.codegym.model.supply.Warehouse;
import com.codegym.service.HistoryRequestBuySupplyService;
import com.codegym.service.SupplyService;

import java.util.Scanner;

public class WarehouseManagerFunction implements Command {
    private final  Warehouse warehouse = new Warehouse();
    private static final int LOW_SUPPLY_THRESHOLD = 10;

    private final SupplyService supplyService = SupplyService.getSupplyService();
    private final  HistoryRequestBuySupplyService historyRequestBuySupplyService = HistoryRequestBuySupplyService.getHistoryRequestBuySupplyService();

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        int inputMenuWarehouseManagerSelected;

        do {
            System.out.println("=== MENU NHÂN VIÊN QUẢN LÝ KHO ===");
            System.out.println("1. Xem danh sách nguyên vật liệu trong kho.");
            System.out.println("2. Kiểm tra số lượng nguyên vật liệu trong kho.");
            System.out.println("3. Yêu cầu mua nguyên vật liệu.");
            System.out.println("4. Trở về Menu chính.");
            inputMenuWarehouseManagerSelected = scanner.nextInt();

            switch (inputMenuWarehouseManagerSelected) {
                case 1:
                    showSupplyListInWarehouse();
                    break;
                case 2:
                    checkSupplyQuantity();
                    break;
                case 3:
                    System.out.println("=== Yêu cầu mua nguyên vật liệu ===");
                    System.out.println("Nhập ID nguyên vật liệu cần mua: ");
                    int supplyIdToRequest = scanner.nextInt();
                    if (isLowSupply(supplyIdToRequest, LOW_SUPPLY_THRESHOLD)) {
                        requestBuySupply(supplyIdToRequest);
                    } else {
                        System.out.println("Số lượng nguyên vật liệu đủ, không cần yêu cầu mua.");
                    }
                    break;
                case 4:
                    System.out.println("4. Trở về Menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (inputMenuWarehouseManagerSelected != 4);
    }

    private void showSupplyListInWarehouse() {
        System.out.println("Danh sách nguyên vật liệu trong kho:");
        warehouse.display();
    }
    private void checkSupplyQuantity() {
        System.out.println("Kiểm tra số lượng nguyên vật liệu trong kho:");
        warehouse.getSupplyQuantities().forEach((supplyId, quantity) -> {
            Supply supply = supplyService.findById(supplyId);
            if (supply != null) {
                if (isLowSupply(supplyId, LOW_SUPPLY_THRESHOLD)) {
                    System.out.println("Cảnh báo: Số lượng nguyên vật liệu " + supply.getSupplyType() + " (ID: " + supplyId + ") trong kho thấp hơn " + LOW_SUPPLY_THRESHOLD + ". Cần mua thêm.");
                }
            }
        });
    }


    private void requestBuySupply(int supplyId) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập số lượng nguyên vật liệu cần mua:");
        int quantityToBuy = scanner.nextInt();

        historyRequestBuySupplyService.addHistoryRequestBuySupply(supplyId, quantityToBuy);
        System.out.println("Đã gửi yêu cầu mua " + quantityToBuy + " nguyên liệu (ID: " + supplyId + ") thành công.");
    }


    private boolean isLowSupply(int supplyId, int threshold) {
        int currentQuantity = warehouse.getSupplyQuantities().getOrDefault(supplyId, 0);
        String supplyName = warehouse.getSupplyData().getOrDefault(supplyId, "");

        if (currentQuantity < threshold) {
            System.out.println("Số lượng nguyên liệu (ID: " + supplyId + ", Tên: " + supplyName + ") trong kho thấp hơn " + threshold );
            return true;
        } else {
            System.out.println("Số lượng nguyên liệu (ID: " + supplyId + ") trong kho đủ. Không cần mua thêm.");
            return false;
        }
    }
}

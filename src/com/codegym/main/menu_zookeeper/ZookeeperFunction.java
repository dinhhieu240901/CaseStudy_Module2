package com.codegym.main.menu_zookeeper;

import com.codegym.main.Command.Command;
import com.codegym.model.Cage.Cage;
import com.codegym.service.CageService;

import java.util.List;
import java.util.Scanner;

public class ZookeeperFunction implements Command {


    CageService cageService = CageService.getCageService();


    @Override
    public void execute() {
            while (true) {
                System.out.println("=== CHĂM SÓC CHUỒNG THÚ ===");
                System.out.println("1. Danh sách chuồng thú");
                System.out.println("2. Xem thông tin chuồng đang quản lý");
                System.out.println("3. Cho thú ăn");
                System.out.println("4. Chăm sóc chuồng");
                System.out.println("5. Thoát về Menu chính");
                Scanner scanner = new Scanner(System.in);
                int inputMenuManagerStaffSelected = scanner.nextInt();
                switch (inputMenuManagerStaffSelected) {
                    case 1:
                        for (Cage cage : cageService.getCages()) {
                            System.out.println(cage);
                        }
                        break;
                    case 2:
                        List<Cage> userCages = cageService.getCagesForUser();
                        if (userCages.isEmpty()) {
                            System.out.println("Không có chuồng nào được quản lý bởi nhân viên này.");
                        } else {
                            for (Cage cage : userCages) {
                                System.out.println(cage);
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Nhập Id chuồng thú muốn cho ăn:");
                        String cageId = scanner.next();
                        System.out.println("Nhập số lượng thức ăn muốn cho:");
                        int foodQuantity = scanner.nextInt();
                        cageService.feedAnimals(cageId, foodQuantity);
                        break;

                    case 4:
                        System.out.println("Nhập Id chuồng thú muốn chăm sóc:");
                        cageService.cleanEnclosure(scanner.next());
                        break;
                    case 5:
                        return;
                }
            }

    }
}

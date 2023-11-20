package com.codegym.main.Command;

import com.codegym.main.*;
import com.codegym.main.menu_accountant.AccountantScreenActivity;
import com.codegym.main.menu_supply.SupplyCommand;
import com.codegym.main.menu_visitor.VisitorCommand;
import com.codegym.main.menu_warehouse.WareHouseScreenActivity;
import com.codegym.main.menu_zookeeper.ZookeeperScreenActivity;
import com.codegym.service.BaseService;
import com.codegym.service.UserService;

import java.util.Scanner;

public class LoginCommand implements Command {
    private UserService userService = UserService.getInstanceUserService();
    private BaseService baseService = new BaseService();

    @Override
    public void execute() {
        Scanner scanner = MainActivity.getInstanceMainActivity().getScanner();
        int inputDataMainSelected = scanner.nextInt();
        scanner.nextLine();
        handleSelection(inputDataMainSelected, scanner);
    }

    private void handleSelection(int selection, Scanner scanner) {
        String expectedRole;
        switch (selection) {
            case 1:
                expectedRole = "Owner";
                break;
            case 2:
                expectedRole = "Zookeeper";
                break;
            case 3:
                expectedRole = "Accountant";
                break;
            case 4:
                expectedRole = "WarehouseManager";
                break;
            case 5:
                SupplyCommand supplyCommand = new SupplyCommand();
                supplyCommand.execute();
                return;
            case 6:
                VisitorCommand visitorCommand = new VisitorCommand();
                visitorCommand.execute();
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }
        handleLogin(scanner, expectedRole);
    }

    private void handleLogin(Scanner scanner, String expectedRole) {
        System.out.println("=== Login ===");
        System.out.println("Username:");
        String username = scanner.next();
        System.out.println("Password:");
        String password = scanner.next();
        userService.login(username, password);
        String userRole = baseService.getRole();
        if (userRole == null || userRole.isEmpty() || !userRole.equals(expectedRole)) {
            System.out.println("Đăng nhập không thành công. Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.");
            return;
        }
        redirectToRoleScreen(userRole);
    }

    private void redirectToRoleScreen(String userRole) {
        ScreenActivity screenActivity;
        switch (userRole) {
            case "Owner":
                screenActivity = new OwnerScreenActivity();
                break;
            case "Zookeeper":
                screenActivity = new ZookeeperScreenActivity();
                break;
            case "Accountant":
                screenActivity = new AccountantScreenActivity();
                break;
            case "WarehouseManager":
                screenActivity = new WareHouseScreenActivity();
                break;
            default:
                System.out.println("Vai trò không hợp lệ. Hãy liên hệ quản trị viên.");
                return;
        }
        screenActivity.renderWindow();
    }
}

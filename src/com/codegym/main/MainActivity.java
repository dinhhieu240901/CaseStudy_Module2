package com.codegym.main;

import com.codegym.main.Command.LoginCommand;
import com.codegym.main.Command.Menu;
import com.codegym.main.Command.MenuItem;
import com.codegym.main.Command.MenuMain;
import com.codegym.main.menu_visitor.VisitorCommand;
import com.codegym.service.BaseService;
import com.codegym.service.UserService;

import java.util.Scanner;

public class MainActivity extends BaseService {
    private static MainActivity mainActivity;
    private final UserService userService = UserService.getInstanceUserService();

    private Scanner scanner = new Scanner(System.in);
    private final Menu menu;
    private MainActivity(){menu = createMenu();}
    public static MainActivity getInstanceMainActivity() {
        if (mainActivity == null) {
            mainActivity = new MainActivity();
        }
        return mainActivity;
    }
    private Menu createMenu() {
        Menu mainMenu = new MenuMain();
        mainMenu.addMenuItem(new MenuItem("Đăng nhập Owner", new LoginCommand()));
        mainMenu.addMenuItem(new MenuItem("Đăng nhập Zookeeper", new LoginCommand()));
        mainMenu.addMenuItem(new MenuItem("Đăng nhập Thu ngân", new LoginCommand()));
        mainMenu.addMenuItem(new MenuItem("Đăng nhập quản lý nguyên liệu", new LoginCommand()));
        mainMenu.addMenuItem(new MenuItem("Nguyên liệu cung cấp",new VisitorCommand()));
        mainMenu.addMenuItem(new MenuItem("Khách tham quan", new VisitorCommand()));
        return mainMenu;
    }

    public void showMainActivity() {
        while (true) {

            System.out.println("=== MENU CHÍNH ===");
            menu.display();
            LoginCommand loginCommand = new LoginCommand();
            loginCommand.execute();
        }
    }
    public Scanner getScanner() {
        return scanner;
    }
}
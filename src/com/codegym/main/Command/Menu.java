package com.codegym.main.Command;

import java.util.List;

public interface Menu {
    void addMenuItem(MenuItem menuItem);
    void display();
    void runCommand(int index);
    List<MenuItem> getMenuItems();
    int size();
}

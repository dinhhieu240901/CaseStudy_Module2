package com.codegym.main.Command;

import com.codegym.main.Command.Command;

public class MenuItem {
    private final String name;
    private final Command command;

    public MenuItem(String name, Command command) {
        this.name = name;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public Command getCommand() {
        return command;
    }
}
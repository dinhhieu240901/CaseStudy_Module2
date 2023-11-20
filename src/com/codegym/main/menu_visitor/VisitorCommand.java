package com.codegym.main.menu_visitor;

import com.codegym.main.Command.Command;
import com.codegym.main.ScreenActivity;

public class VisitorCommand implements Command {
    @Override
    public void execute() {
        ScreenActivity visitorScreen = new VisitorScreenActivity();
        visitorScreen.renderWindow();
    }
}

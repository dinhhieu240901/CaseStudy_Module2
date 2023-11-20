package com.codegym.main.menu_supply;

import com.codegym.main.Command.Command;
import com.codegym.main.ScreenActivity;

public class SupplyCommand implements Command {
    @Override
    public void execute() {
        ScreenActivity supplierScreen = new SupplyScreenActivity();
        supplierScreen.renderWindow();
    }
}

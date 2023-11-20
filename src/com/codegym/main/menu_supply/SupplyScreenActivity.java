package com.codegym.main.menu_supply;

import com.codegym.main.Command.Command;
import com.codegym.main.ScreenActivity;

public class SupplyScreenActivity extends ScreenActivity {
    @Override
    public Command createScreen() {
        return new SupplyFunction();
    }
}

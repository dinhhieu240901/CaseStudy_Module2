package com.codegym.main.menu_warehouse;

import com.codegym.main.Command.Command;
import com.codegym.main.ScreenActivity;

public class WareHouseScreenActivity extends ScreenActivity {
    @Override
    public Command createScreen() {
        return new WarehouseManagerFunction();
    }
}

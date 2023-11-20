package com.codegym.main;

import com.codegym.main.Command.Command;
import com.codegym.main.menu_owner.OwnerFunction;

public class OwnerScreenActivity extends ScreenActivity{
    @Override
    public Command createScreen() {
        return new OwnerFunction();
    }
}

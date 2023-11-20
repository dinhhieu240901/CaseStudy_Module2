package com.codegym.main.menu_visitor;

import com.codegym.main.Command.Command;
import com.codegym.main.ScreenActivity;

public class VisitorScreenActivity extends ScreenActivity {
    @Override
    public Command createScreen() {
        return new VisitorFunction();
    }
}

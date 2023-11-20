package com.codegym.main.menu_accountant;

import com.codegym.main.Command.Command;
import com.codegym.main.ScreenActivity;

public class AccountantScreenActivity extends ScreenActivity {
    @Override
    public Command createScreen() {
        return new AccountantFunction();
    }
}

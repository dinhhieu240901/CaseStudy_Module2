package com.codegym.main;

import com.codegym.main.Command.Command;

public abstract class ScreenActivity {
    public void renderWindow() {
        Command mainFunction = createScreen();
        mainFunction.execute();
    }
    public abstract Command createScreen();

}

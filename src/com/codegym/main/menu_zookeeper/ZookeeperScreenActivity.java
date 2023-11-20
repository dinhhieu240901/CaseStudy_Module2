package com.codegym.main.menu_zookeeper;

import com.codegym.main.Command.Command;
import com.codegym.main.ScreenActivity;

public class ZookeeperScreenActivity extends ScreenActivity {
    @Override
    public Command createScreen() {
        return new ZookeeperFunction();
    }
}

package cn.ycraft.lib.gui.listener;

import cn.ycraft.lib.gui.ChestGUI;
import org.bukkit.event.Listener;

public class InventoryListener implements Listener {
    private final ChestGUI gui;

    public InventoryListener(ChestGUI gui) {
        this.gui = gui;
    }
}

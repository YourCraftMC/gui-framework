package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.context.gui.GUICloseContext;
import org.bukkit.inventory.ItemStack;

public class SimpleGUICloseContext extends SimpleGUIContext implements GUICloseContext {
    public SimpleGUICloseContext(Object event, GUI<?> gui) {
        super(event, gui, -1, null);
    }
}

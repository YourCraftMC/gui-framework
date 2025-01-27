package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.context.gui.GUIOpenContext;
import org.bukkit.inventory.ItemStack;

public class SimpleGUIOpenContext extends SimpleGUIContext implements GUIOpenContext {
    private boolean cancelled;

    public SimpleGUIOpenContext(Object event, GUI<?> gui, int rawSlot, ItemStack cursor) {
        super(event, gui, rawSlot, cursor);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}

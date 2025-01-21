package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.context.gui.GUIClickContext;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SimpleGUIClickContext extends SimpleGUIContext implements GUIClickContext {
    private final ClickType clickType;
    private final int slot;
    private boolean cancelled = true;

    public SimpleGUIClickContext(PacketReceiveEvent event, GUI<?> gui, int rawSlot, int inventorySlot, ItemStack cursor, ClickType clickType, int slot) {
        super(event, gui, rawSlot, inventorySlot, cursor);
        this.clickType = clickType;
        this.slot = slot;
    }

    @Override
    public @NotNull ClickType clickType() {
        return this.clickType;
    }

    @Override
    public int slot() {
        return this.slot;
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

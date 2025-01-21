package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.context.gui.GUIDropContext;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SimpleGUIDropContext extends SimpleGUIContext implements GUIDropContext {
    private final DropType dropType;
    private boolean cancelled = true;

    public SimpleGUIDropContext(PacketReceiveEvent event, GUI<?> gui, int rawSlot, int inventorySlot, ItemStack cursor, DropType dropType) {
        super(event, gui, rawSlot, inventorySlot, cursor);
        this.dropType = dropType;
    }

    @Override
    public @NotNull DropType dropType() {
        return this.dropType;
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

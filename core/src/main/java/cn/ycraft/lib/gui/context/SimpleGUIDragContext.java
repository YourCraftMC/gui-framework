package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.context.gui.GUIDragContext;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SimpleGUIDragContext extends SimpleGUIContext implements GUIDragContext {
    private final DragType dragType;
    private final DragState dragState;
    private boolean cancelled = true;

    public SimpleGUIDragContext(Object event, GUI<?> gui, int rawSlot, ItemStack cursor, DragType dragType, DragState dragState) {
        super(event, gui, rawSlot, cursor);
        this.dragType = dragType;
        this.dragState = dragState;
    }

    @Override
    public @NotNull DragType dragType() {
        return this.dragType;
    }

    @Override
    public @NotNull DragState dragState() {
        return this.dragState;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = true;
    }
}

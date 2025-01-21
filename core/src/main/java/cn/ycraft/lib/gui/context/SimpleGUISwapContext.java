package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.context.gui.GUISwapContext;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Range;

public class SimpleGUISwapContext extends SimpleGUIContext implements GUISwapContext {
    private final int slot;
    private final int hotbarSlot;
    private boolean cancelled = true;

    public SimpleGUISwapContext(PacketReceiveEvent event, GUI<?> gui, int rawSlot, ItemStack cursor, int slot, int hotbarSlot) {
        super(event, gui, rawSlot, cursor);
        this.slot = slot;
        this.hotbarSlot = hotbarSlot;
    }

    @Override
    public int slot() {
        return this.slot;
    }

    @Override
    public @Range(from = 0, to = 8) int hotbarSlot() {
        return this.hotbarSlot;
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

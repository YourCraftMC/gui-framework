package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.util.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleGUIContext implements GUIContext {
    private final PacketReceiveEvent event;
    private final GUI<?> gui;
    private final int rawSlot;
    private final int inventorySlot;
    private ItemStack cursor;

    public SimpleGUIContext(PacketReceiveEvent event, GUI<?> gui, int rawSlot, ItemStack cursor) {
        this.event = event;
        this.gui = gui;
        this.rawSlot = rawSlot;
        this.inventorySlot = convertSlot(rawSlot);
        this.cursor = cursor;
    }

    public final int convertSlot(int rawSlot) {
        int numInTop = type().size();
        if (rawSlot < numInTop) {
            return rawSlot;
        }
        int slot = rawSlot - numInTop;
        if (slot >= 27) slot -= 27;
        else slot += 9;
        return slot;
    }

    @Override
    public @NotNull Object event() {
        return this.event;
    }

    @Override
    public @NotNull GUI<?> gui() {
        return this.gui;
    }

    @Override
    public int rawSlot() {
        return this.rawSlot;
    }

    @Override
    public int inventorySlot() {
        return this.inventorySlot;
    }

    @Override
    public @Nullable ItemStack getCursor() {
        return this.cursor;
    }

    @Override
    public void setCursor(@Nullable ItemStack item) {
        this.cursor = item;
        event.getUser().sendPacket(PacketUtil.setCursor(PacketUtil.fromItem(item)));
        event.getUser().sendPacket(PacketUtil.legacyCursorItem(PacketUtil.fromItem(item)));
    }
}

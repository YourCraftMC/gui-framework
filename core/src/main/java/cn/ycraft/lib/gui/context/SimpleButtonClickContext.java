package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.component.GUIFrame;
import cn.ycraft.lib.gui.context.button.ButtonClickContext;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleButtonClickContext extends SimpleGUIClickContext implements ButtonClickContext {
    private final GUIButton button;
    private final GUIFrame frame;

    public SimpleButtonClickContext(PacketReceiveEvent event, GUI<?> gui, int rawSlot, int inventorySlot, ItemStack cursor, ClickType clickType, int slot, GUIButton button, GUIFrame frame) {
        super(event, gui, rawSlot, inventorySlot, cursor, clickType, slot);
        this.button = button;
        this.frame = frame;
    }

    @Override
    public @NotNull GUIButton button() {
        return this.button;
    }

    @Override
    public @Nullable GUIFrame frame() {
        return this.frame;
    }
}

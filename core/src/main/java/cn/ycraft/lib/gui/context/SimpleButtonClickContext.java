package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.component.GUIFrame;
import cn.ycraft.lib.gui.context.button.ButtonClickContext;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleButtonClickContext extends SimpleGUIClickContext implements ButtonClickContext {
    private final GUIButton button;
    private final GUIFrame frame;

    public SimpleButtonClickContext(Object event, GUI<?> gui, int rawSlot, ItemStack cursor, ClickType clickType, int slot, GUIButton button, GUIFrame frame) {
        super(event, gui, rawSlot, cursor, clickType, slot);
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

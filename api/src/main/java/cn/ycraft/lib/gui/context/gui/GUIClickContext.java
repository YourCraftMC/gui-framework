package cn.ycraft.lib.gui.context.gui;

import cn.ycraft.lib.gui.context.CancellableContext;
import cn.ycraft.lib.gui.context.GUIContext;
import org.jetbrains.annotations.NotNull;

public interface GUIClickContext extends GUIContext, CancellableContext {

    /**
     * Click type in minecraft.
     * <br> See <a href="https://minecraft.wiki/w/Minecraft_Wiki:Projects/wiki.vg_merge/Protocol#Click_Container">Click_Container</a>
     */
    enum ClickType {
        /**
         * Left mouse click to a slot.
         */
        LEFT_CLICK,
        /**
         * Right mouse click to a slot.
         */
        RIGHT_CLICK,
        /**
         * Shift + left mouse click
         */
        SHIFT_LEFT_CLICK,
        /**
         * Shift + right mouse click
         */
        SHIFT_RIGHT_CLICK,
        /**
         * Double click, used for collecting items.
         */
        DOUBLE_CLICK,
        /**
         * Middle click, only defined for creative players in non-player inventories.
         */
        MIDDLE_CLICK,
    }

    @NotNull ClickType clickType();

    default boolean isShiftClick() {
        return clickType() == ClickType.SHIFT_LEFT_CLICK || clickType() == ClickType.SHIFT_RIGHT_CLICK;
    }

    int slot();


}

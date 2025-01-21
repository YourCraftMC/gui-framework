package cn.ycraft.lib.gui.context.gui;

import cn.ycraft.lib.gui.context.CancellableContext;
import cn.ycraft.lib.gui.context.GUIContext;
import org.jetbrains.annotations.NotNull;

public interface GUIDropContext extends GUIContext, CancellableContext {
    /**
     * Drop type in minecraft.
     * <br> See <a href="https://minecraft.wiki/w/Minecraft_Wiki:Projects/wiki.vg_merge/Protocol#Click_Container">Click_Container</a>
     */
    enum DropType {
        DROP_SLOT_ONE, DROP_SLOT_ALL,
        DROP_CURSOR_ONE, DROP_CURSOR_ALL;
    }

    @NotNull DropType dropType();


}

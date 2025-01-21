package cn.ycraft.lib.gui.context.gui;

import cn.ycraft.lib.gui.context.CancellableContext;
import cn.ycraft.lib.gui.context.GUIContext;
import org.jetbrains.annotations.NotNull;

public interface GUIDragContext extends GUIContext, CancellableContext {

    /**
     * Drag type in minecraft.
     * <br> See <a href="https://minecraft.wiki/w/Minecraft_Wiki:Projects/wiki.vg_merge/Protocol#Click_Container">Click_Container</a>
     */
    enum DragType {
        LEFT, RIGHT, MIDDLE
    }

    enum DragState {
        START, ADD, END
    }

    @NotNull DragType dragType();

    @NotNull DragState dragState();


}

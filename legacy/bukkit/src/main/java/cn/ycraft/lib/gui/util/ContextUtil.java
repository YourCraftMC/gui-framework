package cn.ycraft.lib.gui.util;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.context.SimpleGUIClickContext;
import cn.ycraft.lib.gui.context.SimpleGUIDragContext;
import cn.ycraft.lib.gui.context.SimpleGUIDropContext;
import cn.ycraft.lib.gui.context.SimpleGUISwapContext;
import cn.ycraft.lib.gui.context.gui.GUIClickContext;
import cn.ycraft.lib.gui.context.gui.GUIDragContext;
import cn.ycraft.lib.gui.context.gui.GUIDropContext;
import cn.ycraft.lib.gui.context.gui.GUISwapContext;
import org.bukkit.event.inventory.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContextUtil {

    private static final Map<ClickType, GUIClickContext.ClickType> CLICK_MAP = Collections.unmodifiableMap(new HashMap<ClickType, GUIClickContext.ClickType>() {{

        put(ClickType.LEFT, GUIClickContext.ClickType.LEFT_CLICK);
        put(ClickType.RIGHT, GUIClickContext.ClickType.RIGHT_CLICK);
        put(ClickType.SHIFT_LEFT, GUIClickContext.ClickType.SHIFT_LEFT_CLICK);
        put(ClickType.SHIFT_RIGHT, GUIClickContext.ClickType.SHIFT_RIGHT_CLICK);
        put(ClickType.DOUBLE_CLICK, GUIClickContext.ClickType.DOUBLE_CLICK);
        put(ClickType.MIDDLE, GUIClickContext.ClickType.MIDDLE_CLICK);
    }});

    public static boolean isClickContext(InventoryClickEvent e) {
        return CLICK_MAP.containsKey(e.getClick());
    }

    public static boolean isDropContext(InventoryClickEvent e) {
        return e.getAction().name().startsWith("DROP_");
    }

    public static boolean isSwapContext(InventoryClickEvent e) {
        return e.getAction() == InventoryAction.SWAP_WITH_CURSOR || e.getAction() == InventoryAction.HOTBAR_SWAP;
    }

    public static GUIClickContext toClickContext(InventoryClickEvent e, GUI<?> gui) {
        GUIClickContext.ClickType clickType = CLICK_MAP.get(e.getClick());
        if (clickType == null) {
            return null;
        }
        return new SimpleGUIClickContext(e, gui, e.getSlot(), e.getCursor(), clickType, e.getRawSlot());
    }

    public static GUIDragContext toDragContext(InventoryDragEvent e, GUI<?> gui) {
        return new SimpleGUIDragContext(e, gui, -1, e.getCursor(), e.getType() == DragType.SINGLE ? GUIDragContext.DragType.LEFT : GUIDragContext.DragType.RIGHT, GUIDragContext.DragState.END);
    }

    public static GUIDropContext toDropContext(InventoryClickEvent e, GUI<?> gui) {
        GUIDropContext.DropType dropType = null;
        switch (e.getAction()) {
            case DROP_ALL_CURSOR: {
                dropType = GUIDropContext.DropType.DROP_CURSOR_ALL;
                break;
            }
            case DROP_ALL_SLOT: {
                dropType = GUIDropContext.DropType.DROP_SLOT_ALL;
                break;
            }
            case DROP_ONE_CURSOR: {
                dropType = GUIDropContext.DropType.DROP_CURSOR_ONE;
                break;
            }
            case DROP_ONE_SLOT: {
                dropType = GUIDropContext.DropType.DROP_SLOT_ONE;
                break;
            }
        }
        if (dropType == null) {
            return null;
        }
        return new SimpleGUIDropContext(e, gui, e.getRawSlot(), e.getCursor(), dropType);
    }

    public static GUISwapContext toSwapContext(InventoryClickEvent e, GUI<?> gui) {
        return new SimpleGUISwapContext(e, gui, e.getRawSlot(), e.getCursor(), e.getRawSlot(), e.getHotbarButton());
    }
}

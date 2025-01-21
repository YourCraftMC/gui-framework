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
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;

public class ContextUtil {
    public static boolean isClickContext(WrapperPlayClientClickWindow p) {
        WrapperPlayClientClickWindow.WindowClickType mode = p.getWindowClickType();
        boolean special = p.getSlot() == -999;
        if (mode == WrapperPlayClientClickWindow.WindowClickType.PICKUP && !special) {
            return true;
        }
        return mode == WrapperPlayClientClickWindow.WindowClickType.QUICK_MOVE || mode == WrapperPlayClientClickWindow.WindowClickType.CLONE || mode == WrapperPlayClientClickWindow.WindowClickType.PICKUP_ALL;
    }

    public static boolean isDragContext(WrapperPlayClientClickWindow p) {
        WrapperPlayClientClickWindow.WindowClickType mode = p.getWindowClickType();
        return mode == WrapperPlayClientClickWindow.WindowClickType.QUICK_CRAFT;
    }

    public static boolean isDropContext(WrapperPlayClientClickWindow p) {
        WrapperPlayClientClickWindow.WindowClickType mode = p.getWindowClickType();
        boolean special = p.getSlot() == -999;
        if (mode == WrapperPlayClientClickWindow.WindowClickType.PICKUP && special) {
            return true;
        }
        return mode == WrapperPlayClientClickWindow.WindowClickType.THROW;
    }

    public static boolean isSwapContext(WrapperPlayClientClickWindow p) {
        return p.getWindowClickType() == WrapperPlayClientClickWindow.WindowClickType.SWAP;
    }

    public static GUIClickContext toClickContext(PacketReceiveEvent event, GUI<?> gui, WrapperPlayClientClickWindow p) {
        int slot = p.getSlot();

        GUIClickContext.ClickType clickType = toClickType(p);
        if (clickType == null) {
            return null;
        }
        return new SimpleGUIClickContext(event, gui, slot, slot, SpigotConversionUtil.toBukkitItemStack(p.getCarriedItemStack()), clickType, slot);
    }

    public static GUIDragContext toDragContext(PacketReceiveEvent event, GUI<?> gui, WrapperPlayClientClickWindow p) {
        int slot = p.getSlot();

        GUIDragContext.DragType dragType = toDragType(p);
        GUIDragContext.DragState dragState = toDragState(p);
        if (dragType == null || dragState == null) {
            return null;
        }
        return new SimpleGUIDragContext(event, gui, slot, slot, SpigotConversionUtil.toBukkitItemStack(p.getCarriedItemStack()), dragType, dragState);
    }

    public static GUIDropContext toDropContext(PacketReceiveEvent event, GUI<?> gui, WrapperPlayClientClickWindow p) {
        int slot = p.getSlot();

        GUIDropContext.DropType dropType = toDropType(p);
        if (dropType == null) {
            return null;
        }
        return new SimpleGUIDropContext(event, gui, slot, slot, SpigotConversionUtil.toBukkitItemStack(p.getCarriedItemStack()), dropType);
    }

    public static GUISwapContext toSwapContext(PacketReceiveEvent event, GUI<?> gui, WrapperPlayClientClickWindow p) {
        int slot = p.getSlot();
        int button = p.getButton();

        GUISwapContext.SwapType swapType = toSwapType(p);
        if (swapType == null) {
            return null;
        }
        return new SimpleGUISwapContext(event, gui, slot, slot, SpigotConversionUtil.toBukkitItemStack(p.getCarriedItemStack()), slot, button);
    }

    public static GUIClickContext.ClickType toClickType(WrapperPlayClientClickWindow p) {
        WrapperPlayClientClickWindow.WindowClickType mode = p.getWindowClickType();
        int button = p.getButton();
        boolean special = p.getSlot() == -999;
        if (mode == WrapperPlayClientClickWindow.WindowClickType.PICKUP) {
            if (button == 0 && !special) {
                return GUIClickContext.ClickType.LEFT_CLICK;
            } else if (button == 1 && !special) {
                return GUIClickContext.ClickType.RIGHT_CLICK;
            } else {
                throw new IllegalArgumentException("Unknown click type");
            }
        } else if (mode == WrapperPlayClientClickWindow.WindowClickType.QUICK_MOVE) {
            if (button == 0) {
                return GUIClickContext.ClickType.SHIFT_LEFT_CLICK;
            } else if (button == 1) {
                return GUIClickContext.ClickType.SHIFT_RIGHT_CLICK;
            } else {
                throw new IllegalArgumentException("Unknown click type");
            }
        } else if (mode == WrapperPlayClientClickWindow.WindowClickType.CLONE) {
            if (button == 2) {
                return GUIClickContext.ClickType.MIDDLE_CLICK;
            } else {
                throw new IllegalArgumentException("Unknown click type");
            }
        } else if (mode == WrapperPlayClientClickWindow.WindowClickType.PICKUP_ALL) {
            if (button == 0) {
                return GUIClickContext.ClickType.DOUBLE_CLICK;
            } else if (button == 1) {
                return GUIClickContext.ClickType.PICKUP_ALL;
            } else {
                throw new IllegalArgumentException("Unknown click type");
            }
        }
        return null;
    }

    public static GUIDragContext.DragType toDragType(WrapperPlayClientClickWindow p) {
        WrapperPlayClientClickWindow.WindowClickType mode = p.getWindowClickType();
        if (mode != WrapperPlayClientClickWindow.WindowClickType.QUICK_CRAFT) {
            return null;
        }
        int button = p.getButton();
        if (button == 0 || button == 1 || button == 2) {
            return GUIDragContext.DragType.LEFT;
        } else if (button == 4 || button == 6 || button == 5) {
            return GUIDragContext.DragType.RIGHT;
        } else if (button == 8 || button == 10 || button == 9) {
            return GUIDragContext.DragType.MIDDLE;
        }
        return null;
    }

    public static GUIDragContext.DragState toDragState(WrapperPlayClientClickWindow p) {
        WrapperPlayClientClickWindow.WindowClickType mode = p.getWindowClickType();
        if (mode != WrapperPlayClientClickWindow.WindowClickType.QUICK_CRAFT) {
            return null;
        }
        int button = p.getButton();
        boolean special = p.getSlot() == -999;
        if (special) {
            if (button == 0 || button == 4 || button == 8) {
                return GUIDragContext.DragState.START;
            } else if (button == 2 || button == 6 || button == 10) {
                return GUIDragContext.DragState.END;
            }
        } else if (button == 1 || button == 5 || button == 9) {
            return GUIDragContext.DragState.ADD;
        }
        return null;
    }

    public static GUIDropContext.DropType toDropType(WrapperPlayClientClickWindow p) {
        WrapperPlayClientClickWindow.WindowClickType mode = p.getWindowClickType();
        int button = p.getButton();
        boolean special = p.getSlot() == -999;
        if (mode == WrapperPlayClientClickWindow.WindowClickType.PICKUP && special) {
            if (button == 0) {
                return GUIDropContext.DropType.DROP_CURSOR_ALL;
            } else if (button == 1) {
                return GUIDropContext.DropType.DROP_CURSOR_ONE;
            } else {
                throw new IllegalArgumentException("Unknown click type");
            }
        }
        if (mode == WrapperPlayClientClickWindow.WindowClickType.THROW) {
            if (button == 0) {
                return GUIDropContext.DropType.DROP_SLOT_ONE;
            } else if (button == 1) {
                return GUIDropContext.DropType.DROP_SLOT_ALL;
            } else {
                throw new IllegalArgumentException("Unknown click type");
            }
        }
        return null;
    }

    public static GUISwapContext.SwapType toSwapType(WrapperPlayClientClickWindow p) {
        WrapperPlayClientClickWindow.WindowClickType mode = p.getWindowClickType();
        if (mode != WrapperPlayClientClickWindow.WindowClickType.SWAP) {
            return null;
        }
        int button = p.getButton();
        if (button >= 0 && button <= 8) {
            return GUISwapContext.SwapType.valueOf("HOR_BAR_" + (button + 1));
        }
        if (button == 40) {
            return GUISwapContext.SwapType.OFFHAND;
        }
        if (button > 8) {
            return GUISwapContext.SwapType.UNKNOWN;
        }
        return null;
    }
}

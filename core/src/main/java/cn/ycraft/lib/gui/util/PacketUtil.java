package cn.ycraft.lib.gui.util;

import cn.ycraft.lib.gui.click.ClickMeta;
import cn.ycraft.lib.gui.click.GuiClickRequest;
import cn.ycraft.lib.gui.click.type.ClickType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
public class PacketUtil {
    public static GuiClickRequest toRequest(WrapperPlayClientClickWindow p) {
        GuiClickRequest request = new GuiClickRequest();
        WrapperPlayClientClickWindow.WindowClickType windowClickType = p.getWindowClickType();
        int mode = windowClickType == WrapperPlayClientClickWindow.WindowClickType.UNKNOWN ? -1 : windowClickType.ordinal();
        request.setDefault(ClickMeta.CLICK_MODE, mode);
        request.setDefault(ClickMeta.CLICK_TYPE, toClickType(p));
        request.setDefault(ClickMeta.SLOT_NUM, p.getSlot());
        request.setDefault(ClickMeta.HOT_BAR, p.getButton());
        return request;
    }

    public static ClickType toClickType(WrapperPlayClientClickWindow p) {
        WrapperPlayClientClickWindow.WindowClickType mode = p.getWindowClickType();
        int button = p.getButton();
        int slot = p.getSlot();
        boolean special = slot == -999;
        switch (mode) {
            case PICKUP: {
                if (button == 0) {
                    return special ? ClickType.LEFT_CLICK_OUTSIDE_DROP_ALL : ClickType.LEFT_CLICK_SLOT;
                } else if (button == 1) {
                    return special ? ClickType.RIGHT_CLICK_OUTSIDE_DROP_SINGLE : ClickType.RIGHT_CLICK_SLOT;
                }
                return ClickType.UNKNOWN;
            }
            case QUICK_MOVE: {
                if (button == 0) {
                    return ClickType.SHIFT_LEFT;
                } else if (button == 1) {
                    return ClickType.SHIFT_RIGHT;
                }
                return ClickType.UNKNOWN;
            }
            case SWAP: {
                if (button >= 0 && button <= 8) {
                    return ClickType.valueOf("HOR_BAR_" + (button + 1));
                }
                if (button == 40) {
                    return ClickType.SWAP_OFFHAND;
                }
                return special ? ClickType.UNKNOWN : ClickType.HOR_BAR_UNDEFINED;
            }
            case CLONE: {
                if (button == 2) {
                    return ClickType.MIDDLE_CLICK;
                }
                return ClickType.UNKNOWN;
            }
            case THROW: {
                if (button == 0) {
                    return ClickType.DROP;
                } else if (button == 1) {
                    return ClickType.CTRL_DROP;
                }
                return ClickType.UNKNOWN;
            }
            case QUICK_CRAFT: {
                return getDragType(special, button);
            }
            case PICKUP_ALL: {
                if (button == 0) {
                    return ClickType.DOUBLE_CLICK;
                } else if (button == 1) {
                    return ClickType.PICKUP_ALL;
                }
            }
            default: {
                return ClickType.UNKNOWN;
            }
        }
    }

    public static ClickType getDragType(boolean special, int button) {
        switch (button) {
            case 0: {
                return special ? ClickType.START_LEFT_DRAG : ClickType.UNKNOWN;
            }
            case 1: {
                return special ? ClickType.UNKNOWN : ClickType.ADD_LEFT_SLOT_DRAG;
            }
            case 2: {
                return special ? ClickType.END_LEFT_DRAG : ClickType.UNKNOWN;
            }
            case 4: {
                return special ? ClickType.START_RIGHT_DRAG : ClickType.UNKNOWN;
            }
            case 5: {
                return special ? ClickType.UNKNOWN : ClickType.ADD_RIGHT_SLOT_DRAG;
            }
            case 6: {
                return special ? ClickType.END_RIGHT_DRAG : ClickType.UNKNOWN;
            }
            case 8: {
                return special ? ClickType.START_MIDDLE_DRAG : ClickType.UNKNOWN;
            }
            case 9: {
                return special ? ClickType.UNKNOWN : ClickType.ADD_MIDDLE_SLOT_DRAG;
            }
            case 10: {
                return special ? ClickType.END_MIDDLE_DRAG : ClickType.UNKNOWN;
            }
            default: {
                return ClickType.UNKNOWN;
            }
        }
    }
}

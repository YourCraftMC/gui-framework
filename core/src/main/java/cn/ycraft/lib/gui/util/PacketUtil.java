package cn.ycraft.lib.gui.util;

import cn.ycraft.lib.gui.holder.InventoryType;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.wrapper.play.server.*;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import net.kyori.adventure.text.Component;

import java.util.List;

public class PacketUtil {
    public static ItemStack fromItem(org.bukkit.inventory.ItemStack itemStack) {
        if (itemStack == null) {
            return ItemStack.EMPTY;
        }
        return SpigotConversionUtil.fromBukkitItemStack(itemStack);
    }

    public static WrapperPlayServerSetCursorItem setCursor(ItemStack itemStack) {
        return new WrapperPlayServerSetCursorItem(itemStack == null ? ItemStack.EMPTY : itemStack);
    }

    public static WrapperPlayServerSetSlot legacyCursorItem(ItemStack itemStack) {
        return new WrapperPlayServerSetSlot(-1, 0, 0, itemStack);
    }

    public static WrapperPlayServerOpenWindow openWindowPacket(int windowId, InventoryType<?> type, String title) {
        WrapperPlayServerOpenWindow openWindow = new WrapperPlayServerOpenWindow(
                windowId, type.type(),
                Component.text(title), type.size(),
                true, 0
        );
        openWindow.setLegacyType(type.legacyType());
        return openWindow;
    }

    public static WrapperPlayServerCloseWindow closeWindowPacket(int windowId) {
        return new WrapperPlayServerCloseWindow(windowId);
    }

    public static WrapperPlayServerWindowItems windowItemsPacket(int windowId, int state, List<ItemStack> items, ItemStack cursor) {
        return new WrapperPlayServerWindowItems(windowId, state, items, cursor == null ? ItemStack.EMPTY : cursor);
    }

    public static WrapperPlayServerSetSlot setSlot(int windowId, int state, int index, ItemStack item) {
        return new WrapperPlayServerSetSlot(windowId, state, index, item == null ? ItemStack.EMPTY : item);
    }

    // 1.9+
    public static WrapperPlayServerSetSlot setPlayerInventory(int index, ItemStack item) {
        return new WrapperPlayServerSetSlot(-2, 0, index, item == null ? ItemStack.EMPTY : item);
    }

}

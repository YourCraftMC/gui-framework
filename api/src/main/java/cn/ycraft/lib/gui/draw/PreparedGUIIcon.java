package cn.ycraft.lib.gui.draw;

import cn.ycraft.lib.gui.slot.GUISlotsHolder;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public interface PreparedGUIIcon<R, S extends PreparedGUIIcon<R, S>> extends GUISlotsHolder<PreparedGUIIcon<R, S>> {

    R commit();

    S item(Supplier<ItemStack> supplier);

    default S item(ItemStack item) {
        return item(() -> item);
    }

}

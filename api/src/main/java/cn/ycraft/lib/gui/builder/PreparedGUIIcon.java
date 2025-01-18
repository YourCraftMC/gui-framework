package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.component.GUIIcon;
import cn.ycraft.lib.gui.slot.GUISlotsHolder;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public interface PreparedGUIIcon<R, S extends PreparedGUIIcon<R, S>> extends GUISlotsHolder<PreparedGUIIcon<R, S>> {

    R commit();

    GUIIcon build();

    S item(Supplier<ItemStack> supplier);

    default S item(ItemStack item) {
        return item(() -> item);
    }

}

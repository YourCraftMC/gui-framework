package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.component.GUIIcon;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public interface PreparedGUIIcon<R, S extends PreparedGUIIcon<R, S>>
        extends PreparedGUISlots<R, PreparedGUIIcon<R, S>> {

    GUIIcon build();

    S item(Supplier<ItemStack> supplier);

    default S item(ItemStack item) {
        return item(() -> item);
    }

}

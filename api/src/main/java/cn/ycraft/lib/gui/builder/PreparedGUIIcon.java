package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.component.GUIIcon;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public interface PreparedGUIIcon<BACKWARDS, SELF extends PreparedGUIIcon<BACKWARDS, SELF>>
        extends PreparedGUISlots<BACKWARDS, SELF> {

    GUIIcon build();

    SELF item(Supplier<ItemStack> supplier);

    default SELF item(ItemStack item) {
        return item(() -> item);
    }

}

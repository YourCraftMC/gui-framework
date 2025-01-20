package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.context.ButtonContext;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public interface PreparedGUIButton<BACKWARDS, SELF extends PreparedGUIButton<BACKWARDS, SELF>>
        extends PreparedGUISlots<BACKWARDS, SELF> {

    @NotNull GUIButton build();

    SELF item(Supplier<ItemStack> supplier);

    default SELF item(ItemStack item) {
        return item(() -> item);
    }

    <C extends ButtonContext> SELF handle(@NotNull Class<C> contextType, @NotNull BiConsumer<Player, C> handler);

    SELF processActions(@NotNull BiConsumer<Player, ButtonContext> handler);

}

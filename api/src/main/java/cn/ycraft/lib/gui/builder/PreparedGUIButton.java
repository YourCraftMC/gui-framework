package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.context.ButtonContext;
import cn.ycraft.lib.gui.slot.GUISlotsHolder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public interface PreparedGUIButton<R, S extends PreparedGUIButton<R, S>> extends GUISlotsHolder<PreparedGUIButton<R, S>> {

    R commit();

    GUIButton build();

    S item(Supplier<ItemStack> supplier);

    default S item(ItemStack item) {
        return item(() -> item);
    }

    <C extends ButtonContext> S handle(@NotNull Class<C> contextType, @NotNull BiConsumer<Player, C> handler);

    S processActions(@NotNull BiConsumer<Player, ButtonContext> handler);

}

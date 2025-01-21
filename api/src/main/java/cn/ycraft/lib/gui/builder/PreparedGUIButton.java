package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.context.button.ButtonClickContext;
import cn.ycraft.lib.gui.context.button.ButtonContext;
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

    default SELF handleClick(@NotNull BiConsumer<Player, ButtonClickContext> handler) {
        return handle(ButtonClickContext.class, handler);
    }

    /**
     * Process all the action contexts
     * <br> will REPLACE the previous combined handler
     *
     * @param handler The handler
     * @return The builder
     */
    SELF process(@NotNull BiConsumer<Player, ButtonContext> handler);

}

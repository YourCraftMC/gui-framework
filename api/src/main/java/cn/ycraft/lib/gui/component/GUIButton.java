package cn.ycraft.lib.gui.component;

import cn.ycraft.lib.gui.builder.PreparedGUIButton;
import cn.ycraft.lib.gui.context.ButtonContext;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * A button that can be clicked.
 */
public interface GUIButton extends GUIIcon {

    static PreparedGUIButton<?, ?> button(@NotNull Supplier<ItemStack> item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static PreparedGUIButton<?, ?> button(ItemStack item) {
        return button(() -> item);
    }

    void trigger(@NotNull Player player, @NotNull ButtonContext context);

}

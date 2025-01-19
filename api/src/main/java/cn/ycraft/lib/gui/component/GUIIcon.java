package cn.ycraft.lib.gui.component;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * Represents an icon in a GUI.
 */
public interface GUIIcon extends Cloneable {

    static GUIIcon icon(@NotNull Supplier<ItemStack> item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static GUIIcon icon(@Nullable ItemStack item) {
        return icon(() -> item);
    }

    /**
     * @return the displayed {@link ItemStack}  for the icon
     */
    @Nullable
    ItemStack item();

    /**
     * Set the displayed {@link ItemStack} for the icon
     *
     * @param supplier Supplied the {@link ItemStack} to display
     */
    void item(@NotNull Supplier<@Nullable ItemStack> supplier);

    /**
     * Set the displayed {@link ItemStack} for the icon
     *
     * @param item the {@link ItemStack} to display
     */
    default void item(@Nullable ItemStack item) {
        item(() -> item);
    }

}

package cn.ycraft.lib.gui.holder;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Map;
import java.util.Set;

/**
 * The holder wrapper for an {@link InventoryType}'s instance.
 * <br> Also the original inventory implementation behind the GUI instance.
 */
public interface InventoryWrapper<T extends InventoryType<T>> {

    @NotNull T type();

    @NotNull
    @Unmodifiable
    Set<Player> viewers();

    @NotNull
    ItemStack[] contents();

    @NotNull
    @Unmodifiable
    Map<Integer, ItemStack> items();

    @NotNull
    String title();

    void title(@NotNull String title);

    void contents(@NotNull ItemStack... contents);

    /**
     * Get the item at the given index.
     *
     * @param index the index
     * @return the item at the given index
     */
    @Nullable
    ItemStack get(int index);

    void set(int index, @Nullable ItemStack item);

    default void clear() {
        contents(new ItemStack[type().size()]);
    }

    void updateView(@NotNull Player viewer);

    void open(@NotNull Player viewer);

    void close(@NotNull Player viewer);

    default void updateView() {
        viewers().forEach(this::updateView);
    }

}

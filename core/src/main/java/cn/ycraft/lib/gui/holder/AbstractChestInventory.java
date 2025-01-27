package cn.ycraft.lib.gui.holder;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;

public abstract class AbstractChestInventory<T extends InventoryType<T>> implements InventoryWrapper<T> {
    protected final T type;
    protected String title = "Undefined";
    protected ItemStack[] items;
    protected final LinkedHashSet<Player> viewers = new LinkedHashSet<>();

    public AbstractChestInventory(T type) {
        this.type = type;
    }

    @Override
    public @NotNull T type() {
        return this.type;
    }

    @Override
    public @NotNull @Unmodifiable Set<Player> viewers() {
        return Collections.unmodifiableSet(viewers);
    }

    @Override
    public @NotNull ItemStack[] contents() {
        return this.items;
    }

    @Override
    public @NotNull Map<Integer, ItemStack> items() {
        Map<Integer, ItemStack> map = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            map.put(i, items[i]);
        }
        return map;
    }

    @Override
    public @NotNull String title() {
        return this.title;
    }

    @Override
    public void title(@NotNull String title) {
        this.title = title;
    }

    @Override
    public void contents(@NotNull ItemStack... contents) {
        if (contents.length != this.type.size()) {
            throw new IllegalArgumentException("The length of the contents array must be equal to the size of the inventory");
        }
        this.items = contents;
    }

    @Override
    public @Nullable ItemStack get(int index) {
        return this.items[index];
    }

    @Override
    public void set(int index, @Nullable ItemStack item) {
        this.items[index] = item;
    }
}

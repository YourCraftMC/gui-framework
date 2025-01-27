package cn.ycraft.lib.gui.holder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChestInventory extends AbstractChestInventory<ChestInventoryType> {
    private final Inventory inventory;

    public ChestInventory(ChestInventoryType type) {
        super(type);
        this.inventory = Bukkit.createInventory(null, type.row() * 9, title);
    }

    @Override
    public int windowId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateView(@NotNull Player viewer) {
        //do nothing
    }

    @Override
    public void open(@NotNull Player viewer) {
        if (this.viewers.add(viewer)) {
            viewer.openInventory(inventory);
        }
    }

    @Override
    public void close(@NotNull Player viewer) {
        if (this.viewers.remove(viewer)) {
            if (viewer.getOpenInventory().getTopInventory() == inventory) {
                viewer.closeInventory();
            }
        }
    }

    @Override
    public void updateView() {
        inventory.setContents(contents());
    }

    @Override
    public void set(int index, @Nullable ItemStack item) {
        super.set(index, item);
        inventory.setItem(index, item);
    }
}

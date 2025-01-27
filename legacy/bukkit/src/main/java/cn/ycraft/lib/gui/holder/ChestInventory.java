package cn.ycraft.lib.gui.holder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

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
        if (this.viewers.contains(viewer)) {
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
}

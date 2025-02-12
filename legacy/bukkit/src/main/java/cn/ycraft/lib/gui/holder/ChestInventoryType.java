package cn.ycraft.lib.gui.holder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class ChestInventoryType extends AbstractChestInventoryType<ChestInventoryType> {

    public ChestInventoryType(@Range(from = 1, to = 6) int row) {
        super(row);
    }

    @Override
    public @NotNull ChestInventory create() {
        return new ChestInventory(this);
    }
}

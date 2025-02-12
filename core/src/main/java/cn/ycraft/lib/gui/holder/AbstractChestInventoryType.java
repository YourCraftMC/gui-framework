package cn.ycraft.lib.gui.holder;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public abstract class AbstractChestInventoryType<T extends InventoryType<T>> implements InventoryType<T> {

    protected final int row;

    public AbstractChestInventoryType(@Range(from = 1, to = 6) int row) {
        this.row = row;
    }

    @Override
    public int type() {
        return row - 1;
    }

    @Override
    public @Nullable String legacyType() {
        return "minecraft:container";
    }

    @Override
    public int row() {
        return this.row;
    }

    @Override
    public int column() {
        return 9;
    }

    @Override
    public int playerIndex(@Range(from = 0, to = 35) int index) {
        return size() + index;
    }
}

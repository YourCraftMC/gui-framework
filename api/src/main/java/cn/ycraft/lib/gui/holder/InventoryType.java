package cn.ycraft.lib.gui.holder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public interface InventoryType<T extends InventoryType<T>> {

    /**
     * The original packet type id of this inventory holder.
     * <br>See <a href="https://minecraft.wiki/w/Minecraft_Wiki:Projects/wiki.vg_merge/Inventory">wiki/Inventory</a>
     *
     * @return the type id
     */
    int type();

    /**
     * The legacy packet type of this inventory holder
     * <br>See <a href="https://minecraft.wiki/w/Minecraft_Wiki:Projects/wiki.vg_merge/Inventory?direction=prev&oldid=2765360">wiki/Inventory(old)</a>
     *
     * @return the legacy type, NULL if not exists
     */
    @Nullable String legacyType();

    /**
     * The row of this inventory holder's display.
     *
     * @return the row
     */
    int row();

    /**
     * The column of this inventory holder's display.
     *
     * @return the column
     */
    int column();

    /**
     * The max content size of this inventory holder's display.
     *
     * @return the size
     */
    default int size() {
        return column() * row();
    }

    /**
     * Calculate the data index of the slot in the inventory holder's display.
     *
     * @param index the index of the slot in player's inventory
     * @return the data index of current inventory holder
     */
    int playerIndex(@Range(from = 0, to = 35) int index);

    /**
     * The index of the slot in the inventory holder's display.
     *
     * @param row    the row of the slot
     * @param column the column of the slot
     * @return the index
     */
    default int holderIndex(int row, int column) {
        return (row * column()) - (column == column() ? 1 : column() - column + 1);
    }

    @NotNull InventoryWrapper<T> create();

}

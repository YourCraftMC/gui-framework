package cn.ycraft.lib.gui.unused;


public enum InventoryTypes {

    /**
     * A 1x9 GUI based on Chest.
     */
    ONE_BY_NINE(0, 1, 9),
    /**
     * A 2x9 GUI based on Chest.
     */
    TWO_BY_NINE(1, 2, 9),
    /**
     * A 3x9 GUI based on Chest.
     */
    THREE_BY_NINE(2, 3, 9),
    /**
     * A 4x9 GUI based on Chest.
     */
    FOUR_BY_NINE(3, 4, 9),
    /**
     * A 5x9 GUI based on Chest.
     */
    FIVE_BY_NINE(4, 5, 9),
    /**
     * A 6x9 GUI based on Chest.
     */
    SIX_BY_NINE(5, 6, 9),

    /**
     * A 3x3 GUI based on Dropper.
     */
    THREE_BY_THREE(6, 3, 3),

    FURNACE(14, 3, 1, 3);

    private final int typeInt;
    private final int row;
    private final int column;
    private final int size;

    InventoryTypes(int typeInt, int row, int column) {
        this(typeInt, row, column, row * column);
    }

    InventoryTypes(int typeInt, int row, int column, int size) {
        this.typeInt = typeInt;
        this.row = row;
        this.column = column;
        this.size = size;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public int size() {
        return size; // row * column, but we have a field for it for performance.
    }

    public int type() {
        return typeInt;
    }

}

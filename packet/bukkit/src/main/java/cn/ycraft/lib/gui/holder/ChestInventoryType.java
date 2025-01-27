package cn.ycraft.lib.gui.holder;

import com.github.retrooper.packetevents.PacketEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class ChestInventoryType implements InventoryType<ChestInventoryType> {

    private static final WindowIdGenerator WINDOW_ID_GENERATOR = new WindowIdGenerator(PacketEvents.getAPI().getServerManager().getVersion());

    private final int row;

    public ChestInventoryType(@Range(from = 1, to = 6) int row) {
        this.row = row;
    }

    @Override
    public int type() {
        return row - 1;
    }

    @Override
    public String legacyType() {
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
        return index;
    }

    @Override
    public @NotNull ChestInventory create() {
        return new ChestInventory(this, WINDOW_ID_GENERATOR.nextId());
    }

}

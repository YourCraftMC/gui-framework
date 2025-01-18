package cn.ycraft.lib.gui.holder;

import com.github.retrooper.packetevents.PacketEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class ChestInventory implements InventoryType<ChestInventory> {
    private final int row;

    public ChestInventory(@Range(from = 1, to = 6) int row) {
        this.row = row;
    }

    @Override
    public int type() {
        return row - 1;
    }

    @Override
    public String legacyType() {
        return "Chest";
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

    //todo windowId
    static WindowId windowId = new WindowId(PacketEvents.getAPI().getServerManager().getVersion());

    @Override
    public @NotNull InventoryWrapper<ChestInventory> create() {
        return new ChestInventoryWrapper(windowId.nextId(), this);
    }
}

package cn.ycraft.lib.gui.holder;

import com.github.retrooper.packetevents.PacketEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class ChestInventoryType extends AbstractChestInventoryType<ChestInventoryType> {

    private static final WindowIdGenerator WINDOW_ID_GENERATOR = new WindowIdGenerator(PacketEvents.getAPI().getServerManager().getVersion());

    public ChestInventoryType(@Range(from = 1, to = 6) int row) {
        super(row);
    }

    @Override
    public @NotNull ChestInventory create() {
        return new ChestInventory(this, WINDOW_ID_GENERATOR.nextId());
    }

}

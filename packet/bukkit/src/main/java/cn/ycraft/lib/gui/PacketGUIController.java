package cn.ycraft.lib.gui;

import cn.ycraft.lib.gui.holder.ChestInventoryType;
import com.github.retrooper.packetevents.PacketEventsAPI;
import org.bukkit.entity.HumanEntity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PacketGUIController extends GUIController<ChestInventoryType> {

    protected final PacketEventsAPI<?> api;

    public PacketGUIController(PacketEventsAPI<?> api) {
        super();
        this.api = api;
    }

    public PacketEventsAPI<?> packetEvents() {
        return api;
    }

    @Override
    public AbstractChestGUI<?> createChestGUI(IRow<ChestInventoryType> row) {
        return new ChestGUI(this, row);
    }
}

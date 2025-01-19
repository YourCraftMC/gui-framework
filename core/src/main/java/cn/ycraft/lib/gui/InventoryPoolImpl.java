package cn.ycraft.lib.gui;

import cn.ycraft.lib.gui.holder.ChestInventory;
import cn.ycraft.lib.gui.holder.InventoryType;
import cn.ycraft.lib.gui.holder.InventoryWrapper;
import cn.ycraft.lib.gui.holder.WindowId;
import cn.ycraft.lib.gui.listener.InventoryListener;
import com.github.retrooper.packetevents.PacketEventsAPI;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class InventoryPoolImpl implements InventoryPool {
    private final Map<Integer, InventoryWrapper<?>> inventoryMap = new HashMap<>();
    private final PacketEventsAPI<?> api;
    private final WindowId windowId;

    private InventoryListener listener;

    public InventoryPoolImpl(PacketEventsAPI<?> api) {
        this.api = api;
        this.windowId = new WindowId(api.getServerManager().getVersion());
        initialize();
    }

    public void initialize() {
        api.getEventManager().registerListener(listener = new InventoryListener(this));
    }

    @Override
    public InventoryType<?> chest(int rows) {
        return new ChestInventory(this, rows);
    }

    @Override
    public void closeAll() {
        for (InventoryWrapper<?> wrapper : inventoryMap.values()) {
            for (Player viewer : wrapper.viewers()) {
                wrapper.close(viewer);
            }
        }
        inventoryMap.clear();
        if (listener != null) {
            api.getEventManager().unregisterListener(listener);
            listener = null;
        }
    }

    public int nextWindowId() {
        return windowId.nextId();
    }

    public void addInventory(int id, InventoryWrapper<?> wrapper) {
        //todo id duplicate check?
        if (inventoryMap.containsKey(id)) {
            throw new IllegalArgumentException("Inventory with id " + id + " already exists");
        }
        inventoryMap.put(id, wrapper);
    }

    public InventoryWrapper<?> getInventory(int windowId) {
        return inventoryMap.get(windowId);
    }

    public void removeInventory(int windowId) {
        inventoryMap.remove(windowId);
    }
}

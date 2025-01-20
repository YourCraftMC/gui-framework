package cn.ycraft.lib.gui;

import com.github.retrooper.packetevents.PacketEventsAPI;
import org.bukkit.entity.HumanEntity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GUIController {

    protected final PacketEventsAPI<?> api;
    protected final Map<UUID, GUI<?>> openedGUI = new ConcurrentHashMap<>();

    public GUIController(PacketEventsAPI<?> api) {
        this.api = api;
    }

    public PacketEventsAPI<?> packetEvents() {
        return api;
    }

    public GUI<?> getOpenedGUI(UUID player) {
        return openedGUI.get(player);
    }

    public void setOpenedGUI(UUID player, GUI<?> gui) {
        openedGUI.put(player, gui);
    }

    public void removeOpenedGUI(UUID player) {
        openedGUI.remove(player);
    }

    public void closeAll() {
        Set<HumanEntity> viewers = new HashSet<>();
        openedGUI.values().stream().flatMap(gui -> gui.viewers().stream()).forEach(viewers::add);
        viewers.forEach(HumanEntity::closeInventory);
        openedGUI.clear();
    }


}

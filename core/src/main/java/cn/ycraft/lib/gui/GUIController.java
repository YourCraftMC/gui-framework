package cn.ycraft.lib.gui;

import cn.ycraft.lib.gui.holder.AbstractChestInventoryType;
import org.bukkit.entity.HumanEntity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public abstract class GUIController<C extends AbstractChestInventoryType<C>> {
    protected final Map<UUID, GUI<?>> openedGUI = new ConcurrentHashMap<>();

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

    public abstract AbstractChestGUI<?> createChestGUI(IRow<C> row);
}

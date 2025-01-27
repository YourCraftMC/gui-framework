package cn.ycraft.lib.gui.holder;

import cn.ycraft.lib.gui.util.PacketUtil;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.PacketEventsAPI;
import com.github.retrooper.packetevents.protocol.player.User;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class ChestInventory extends AbstractChestInventory<ChestInventoryType> {

    private final int id;

    private int state;
    private final PacketEventsAPI<?> api;

    public ChestInventory(@NotNull ChestInventoryType type, int id) {
        super(type);
        this.id = id;
        this.items = new ItemStack[type.size()];
        // todo api
        this.api = PacketEvents.getAPI();
    }


    @Override
    public int windowId() {
        return id;
    }

    @Override
    public void updateView(@NotNull Player viewer) {
        if (viewers.contains(viewer)) {
            getUser(viewer).sendPacket(PacketUtil.windowItemsPacket(
                    this.id, incrementStateId(),
                    Arrays.stream(items).map(PacketUtil::fromItem).collect(Collectors.toList()), null
            ));
        }
    }

    @Override
    public void open(@NotNull Player viewer) {
        User user = getUser(viewer);
        user.sendPacket(PacketUtil.openWindowPacket(this.id, type, title));
        user.sendPacket(PacketUtil.windowItemsPacket(
                this.id, incrementStateId(),
                Arrays.stream(items).map(PacketUtil::fromItem).collect(Collectors.toList()), null
        ));
        viewers.add(viewer);
    }

    @Override
    public void close(@NotNull Player viewer) {
        if (viewers.remove(viewer)) {
            getUser(viewer).sendPacket(PacketUtil.closeWindowPacket(this.id));
        }
    }

    private User getUser(@NotNull Player viewer) {
        return api.getPlayerManager().getUser(viewer);
    }

    public int incrementStateId() {
        this.state = this.state + 1 & 32767;
        return this.state;
    }

    public void _removeViewer(UUID uuid) {
        viewers.removeIf(player -> player.getUniqueId().equals(uuid));
    }
}

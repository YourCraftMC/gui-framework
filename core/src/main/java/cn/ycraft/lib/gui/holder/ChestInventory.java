package cn.ycraft.lib.gui.holder;

import cn.ycraft.lib.gui.util.PacketUtil;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.PacketEventsAPI;
import com.github.retrooper.packetevents.protocol.player.User;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;
import java.util.stream.Collectors;

public class ChestInventory implements InventoryWrapper<ChestInventoryType> {

    private final ChestInventoryType type;
    private final int id;

    private int state;
    private String title = "Undefined";
    private ItemStack[] items;
    private final LinkedHashSet<Player> viewers = new LinkedHashSet<>();
    private final PacketEventsAPI<?> api;

    public ChestInventory(@NotNull ChestInventoryType type, int id) {
        this.type = type;
        this.id = id;
        this.items = new ItemStack[type.size()];
        // todo api
        this.api = PacketEvents.getAPI();
    }

    @Override
    public @NotNull ChestInventoryType type() {
        return this.type;
    }

    @Override
    public int windowId() {
        return id;
    }

    @Override
    public @NotNull @Unmodifiable Set<Player> viewers() {
        return Collections.unmodifiableSet(viewers);
    }

    @Override
    public @NotNull ItemStack[] contents() {
        return this.items;
    }

    @Override
    public @NotNull Map<Integer, ItemStack> items() {
        Map<Integer, ItemStack> map = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            map.put(i, items[i]);
        }
        return map;
    }

    @Override
    public @NotNull String title() {
        return this.title;
    }

    @Override
    public void title(@NotNull String title) {
        this.title = title;
    }

    @Override
    public void contents(@NotNull ItemStack... contents) {
        if (contents.length != this.type.size()) {
            throw new IllegalArgumentException("The length of the contents array must be equal to the size of the inventory");
        }
        this.items = contents;
    }

    @Override
    public @Nullable ItemStack get(int index) {
        return this.items[index];
    }

    @Override
    public void set(int index, @Nullable ItemStack item) {
        this.items[index] = item;
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

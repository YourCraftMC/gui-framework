package cn.ycraft.lib.gui.holder;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.PacketEventsAPI;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerCloseWindow;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerOpenWindow;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSetSlot;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerWindowItems;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;
import java.util.stream.Collectors;

public class ChestInventoryWrapper implements InventoryWrapper<ChestInventory> {
    private static final ItemStack EMPTY = new ItemStack(Material.AIR);
    private final int id;
    private int state;
    private final ChestInventory type;
    private String title = "Undefined";
    private ItemStack[] items;
    private final LinkedHashSet<Player> viewers = new LinkedHashSet<>();
    private final PacketEventsAPI<?> api;

    public ChestInventoryWrapper(int id, ChestInventory type) {
        this.id = id;
        this.type = type;
        this.items = new ItemStack[type.size()];
        // todo api
        this.api = PacketEvents.getAPI();
    }

    @Override
    public @NotNull ChestInventory type() {
        return this.type;
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
            getUser(viewer).sendPacket(windowItemsPacket());
        }
    }

    @Override
    public void open(@NotNull Player viewer) {
        User user = getUser(viewer);
        user.sendPacket(openWindowPacket());
        getUser(viewer).sendPacket(windowItemsPacket());
        viewers.add(viewer);
    }

    @Override
    public void close(@NotNull Player viewer) {
        getUser(viewer).sendPacket(closeWindowPacket());
        viewers.remove(viewer);
    }

    private User getUser(@NotNull Player viewer) {
        return api.getPlayerManager().getUser(viewer);
    }

    public int incrementStateId() {
        this.state = this.state + 1 & 32767;
        return this.state;
    }

    public WrapperPlayServerOpenWindow openWindowPacket() {
        WrapperPlayServerOpenWindow openWindow = new WrapperPlayServerOpenWindow(this.id, this.type.type(), Component.text(this.title), this.type.size(), true, 0);
        openWindow.setLegacyType(this.type.legacyType());
        return openWindow;
    }

    public WrapperPlayServerCloseWindow closeWindowPacket() {
        return new WrapperPlayServerCloseWindow(this.id);
    }

    public WrapperPlayServerWindowItems windowItemsPacket() {
        List<com.github.retrooper.packetevents.protocol.item.ItemStack> items = Arrays.stream(this.items)
                .map(SpigotConversionUtil::fromBukkitItemStack)
                .collect(Collectors.toList());
        return new WrapperPlayServerWindowItems(this.id, incrementStateId(), items, null);
    }

    public WrapperPlayServerSetSlot setSlot(int index, ItemStack item) {
        return new WrapperPlayServerSetSlot(this.id, incrementStateId(), index, SpigotConversionUtil.fromBukkitItemStack(item));
    }

    public static WrapperPlayServerSetSlot setCursor(int windowId, ItemStack item) {
        return new WrapperPlayServerSetSlot(-1, 0, 0, SpigotConversionUtil.fromBukkitItemStack(item));
    }

    // 1.9+
    public static WrapperPlayServerSetSlot setPlayerInventory(int index, ItemStack item) {
        return new WrapperPlayServerSetSlot(-2, 0, index, SpigotConversionUtil.fromBukkitItemStack(item));
    }
}

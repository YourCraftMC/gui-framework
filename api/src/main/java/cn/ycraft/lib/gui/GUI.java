package cn.ycraft.lib.gui;

import cn.ycraft.lib.gui.builder.PreparedGUIButton;
import cn.ycraft.lib.gui.component.GUIFrame;
import cn.ycraft.lib.gui.component.GUIIcon;
import cn.ycraft.lib.gui.data.GUIStatements;
import cn.ycraft.lib.gui.builder.PreparedGUIIcon;
import cn.ycraft.lib.gui.holder.InventoryWrapper;
import cn.ycraft.lib.gui.slot.GUISlot;
import cn.ycraft.lib.gui.builder.PreparedGUISlots;
import cn.ycraft.lib.gui.slot.impl.AbstractSlotsHolder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface GUI<W extends InventoryWrapper<?>> extends Cloneable {

    /**
     * @return The original {@link InventoryWrapper}
     */
    W inventory();

    @NotNull GUIStatements flags();

    @NotNull
    default Set<Player> viewers() {
        return inventory().viewers();
    }

    /**
     * @return All registered {@link GUIFrame}s
     */
    List<GUIFrame> areas();

    @Unmodifiable
    @NotNull SortedMap<Integer, GUIIcon> icons();

    void open(Player player);

    void close(Player player);

    void update(@NotNull GUIIcon icon);

    void update(@NotNull GUIFrame area);

    default void update(Player player) {
        inventory().updateView(player);
    }

    default void update() {
        viewers().forEach(this::update);
    }

    void title(@NotNull String title);

    <T extends GUIIcon> PreparedGUISlots<GUI<W>, ?> put(T icon);

    <T extends GUIFrame> void put(T frame);

    PreparedGUIIcon<GUI<W>, ?> icon();

    default PreparedGUIIcon<GUI<W>, ?> icon(ItemStack item) {
        return icon().item(item);
    }

    default PreparedGUIIcon<GUI<W>, ?> icon(Supplier<ItemStack> supplier) {
        return icon().item(supplier);
    }

    PreparedGUIButton<GUI<W>, ?> button();

    PreparedGUISlots<GUI<W>, ?> remove();

    void remove(@NotNull GUIIcon icon);

    void remove(@NotNull GUIFrame frame);


}

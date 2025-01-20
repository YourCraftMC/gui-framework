package cn.ycraft.lib.gui;

import cn.ycraft.lib.gui.builder.PreparedGUIButton;
import cn.ycraft.lib.gui.component.GUIFrame;
import cn.ycraft.lib.gui.component.GUIIcon;
import cn.ycraft.lib.gui.context.GUIContext;
import cn.ycraft.lib.gui.data.GUIStatements;
import cn.ycraft.lib.gui.builder.PreparedGUIIcon;
import cn.ycraft.lib.gui.holder.InventoryWrapper;
import cn.ycraft.lib.gui.builder.PreparedGUISlots;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.function.Supplier;

public interface GUI<W extends InventoryWrapper<?>> extends Cloneable {

    /**
     * @return The original {@link InventoryWrapper}
     */
    W inventory();

    /**
     * @return The {@link GUIStatements} of the GUI
     */
    @NotNull GUIStatements flags();

    /**
     * Get the title of the GUI
     *
     * @return The title of the GUI
     */
    String title();

    /**
     * Set the title of the GUI
     *
     * @param title The title of the GUI
     */
    void title(@NotNull String title);

    @NotNull
    default Set<Player> viewers() {
        return inventory().viewers();
    }

    /**
     * @return All registered {@link GUIFrame}s
     */
    List<GUIFrame> frames();

    @Unmodifiable
    @NotNull SortedMap<Integer, GUIIcon> icons();

    /**
     * Open the GUI for the player
     *
     * @param player The player who will open the GUI
     */
    void open(Player player);

    /**
     * Close the GUI for the player
     *
     * @param player The player who will close the GUI
     */
    void close(Player player);

    void closeAll();

    /**
     * Update the GUI for the specific {@link GUIIcon}.
     * <br> If the icon is not in the GUI, it will take no effect.
     *
     * @param icon The icon to update
     */
    void update(@NotNull GUIIcon icon);

    /**
     * Update the GUI for the specific {@link GUIFrame}.
     *
     * @param frame The frame to update
     */
    void update(@NotNull GUIFrame frame);

    /**
     * Update the GUI for the player
     *
     * @param player The player who will update the GUI
     */
    default void update(Player player) {
        inventory().updateView(player);
    }

    /**
     * Update the GUI for all viewers
     */
    default void update() {
        viewers().forEach(this::update);
    }

    /**
     * Trigger the GUI functions with the given {@link GUIContext}
     *
     * @param viewer  The player who using the GUI
     * @param context The context functions of the GUI
     */
    void trigger(@NotNull Player viewer, @NotNull GUIContext context);

    <T extends GUIIcon> PreparedGUISlots<GUI<W>, ?> put(@NotNull T icon);

    <T extends GUIFrame> void put(@NotNull T frame);

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

package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.holder.InventoryType;
import cn.ycraft.lib.gui.holder.InventoryWrapper;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GUIContext {

    /**
     * @return The original event that triggered this context.
     */
    @NotNull Object event();

    /**
     * @return The GUI that this context is for.
     */
    @NotNull GUI<?> gui();

    default InventoryType<?> type() {
        return inventory().type();
    }

    default InventoryWrapper<?> inventory() {
        return gui().inventory();
    }

    int rawSlot();

    int inventorySlot();

    @Nullable ItemStack getCursor();

    void setCursor(@Nullable ItemStack item);


}

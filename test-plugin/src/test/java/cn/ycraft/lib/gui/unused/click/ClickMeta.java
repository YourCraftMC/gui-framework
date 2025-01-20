package cn.ycraft.lib.gui.unused.click;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.InventoryPool;
import cn.ycraft.lib.gui.unused.click.type.ClickType;
import cn.ycraft.lib.gui.component.GUIFrame;
import cn.ycraft.lib.gui.component.GUIIcon;
import cn.ycraft.lib.gui.holder.InventoryType;
import cn.ycraft.lib.gui.holder.InventoryWrapper;
import cn.ycraft.lib.gui.slot.GUISlot;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface ClickMeta<T> {
    ClickMeta<InventoryPool> INVENTORY_POOL = ClickMeta.of(InventoryPool.class);
    ClickMeta<InventoryType> INVENTORY_TYPE = ClickMeta.of(InventoryType.class);
    ClickMeta<InventoryWrapper> INVENTORY = ClickMeta.of(InventoryWrapper.class);
    ClickMeta<cn.ycraft.lib.gui.GUI> GUI = ClickMeta.of(GUI.class);
    ClickMeta<GUIFrame> FRAME = ClickMeta.of(GUIFrame.class);
    ClickMeta<GUIIcon> ICON = ClickMeta.of(GUIIcon.class);
    ClickMeta<Player> PLAYER = ClickMeta.of(Player.class);
    ClickMeta<Integer> CLICK_MODE = ClickMeta.of(Integer.class);
    ClickMeta<ClickType> CLICK_TYPE = ClickMeta.of(ClickType.class);
    ClickMeta<GUISlot> SLOT = ClickMeta.of(GUISlot.class);
    ClickMeta<Integer> SLOT_NUM = ClickMeta.of(Integer.class);
    ClickMeta<Integer> HOT_BAR = ClickMeta.of(Integer.class);


    @NotNull
    Class<T> metaClass();

    boolean mutable();

    static <T> ClickMeta<T> mutable(@NotNull Class<T> metaClass) {
        return of(metaClass, true);
    }

    static <T> ClickMeta<T> of(@NotNull Class<T> metaClass) {
        return of(metaClass, false);
    }

    @NotNull
    static <T> ClickMeta<T> of(@NotNull Class<T> metaClass, boolean mutable) {
        return new ClickMeta<T>() {
            @Override
            public @NotNull Class<T> metaClass() {
                return metaClass;
            }

            @Override
            public boolean mutable() {
                return mutable;
            }
        };
    }
}

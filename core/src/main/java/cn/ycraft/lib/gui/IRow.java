package cn.ycraft.lib.gui;

import cn.ycraft.lib.gui.holder.InventoryType;

public interface IRow<T extends InventoryType<T>> {
    int row();

    T type();
}

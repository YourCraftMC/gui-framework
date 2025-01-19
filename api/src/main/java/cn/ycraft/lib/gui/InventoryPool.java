package cn.ycraft.lib.gui;

import cn.ycraft.lib.gui.holder.InventoryType;

public interface InventoryPool {
    InventoryType<?> chest(int rows);

    void closeAll();
}

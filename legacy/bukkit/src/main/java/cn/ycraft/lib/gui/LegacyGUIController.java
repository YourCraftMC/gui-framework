package cn.ycraft.lib.gui;

import cn.ycraft.lib.gui.holder.ChestInventoryType;

public class LegacyGUIController extends GUIController<ChestInventoryType> {
    @Override
    public AbstractChestGUI<?> createChestGUI(IRow<ChestInventoryType> row) {
        return new ChestGUI(this, row);
    }
}

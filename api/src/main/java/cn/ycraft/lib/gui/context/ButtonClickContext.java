package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.component.GUIFrame;
import cn.ycraft.lib.gui.component.GUIIcon;
import cn.ycraft.lib.gui.holder.InventoryType;
import cn.ycraft.lib.gui.holder.InventoryWrapper;
import cn.ycraft.lib.gui.slot.GUISlot;
import cn.ycraft.lib.gui.slot.type.ClickType;
import org.bukkit.entity.Player;

public interface ButtonClickContext extends ButtonContext {

//    GUIController controller();

    InventoryType<?> type();

    InventoryWrapper<?> wrapper();

    GUI<?> gui();

    GUIFrame frame();

    GUIIcon icon();

    Player player();

    Integer clickMode();

    ClickType clickType();

    GUISlot slot();

    Integer slotNum();

    Integer hotBar();

}

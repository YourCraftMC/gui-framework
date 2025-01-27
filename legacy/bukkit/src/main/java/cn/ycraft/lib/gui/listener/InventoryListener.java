package cn.ycraft.lib.gui.listener;

import cn.ycraft.lib.gui.ChestGUI;
import cn.ycraft.lib.gui.context.gui.GUIClickContext;
import cn.ycraft.lib.gui.context.gui.GUIDragContext;
import cn.ycraft.lib.gui.context.gui.GUIDropContext;
import cn.ycraft.lib.gui.context.gui.GUISwapContext;
import cn.ycraft.lib.gui.util.ContextUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryListener implements Listener {
    private final ChestGUI gui;

    public InventoryListener(ChestGUI gui) {
        this.gui = gui;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getPlayer();
        if (!gui.isViewer(player)) {
            return;
        }
        gui.close(player);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (!gui.isViewer(player)) {
            return;
        }
        if (ContextUtil.isClickContext(event)) {
            GUIClickContext clickContext = ContextUtil.toClickContext(event, gui);
            if (clickContext != null) {
                gui.trigger(player, clickContext);
            }
        } else if (ContextUtil.isDropContext(event)) {
            GUIDropContext dropContext = ContextUtil.toDropContext(event, gui);
            if (dropContext != null) {
                gui.trigger(player, dropContext);
            }
        } else if (ContextUtil.isSwapContext(event)) {
            GUISwapContext swapContext = ContextUtil.toSwapContext(event, gui);
            gui.trigger(player, swapContext);
        } else {
            System.out.println("Unknown click type: " + event.getClick());
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (!gui.isViewer(player)) {
            return;
        }
        GUIDragContext dragContext = ContextUtil.toDragContext(event, gui);
        gui.trigger(player, dragContext);
    }
}

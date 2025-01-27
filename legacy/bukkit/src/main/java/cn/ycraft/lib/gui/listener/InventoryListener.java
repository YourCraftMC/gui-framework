package cn.ycraft.lib.gui.listener;

import cn.ycraft.lib.gui.ChestGUI;
import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.component.GUIFrame;
import cn.ycraft.lib.gui.context.CancellableContext;
import cn.ycraft.lib.gui.context.GUIContext;
import cn.ycraft.lib.gui.context.SimpleButtonClickContext;
import cn.ycraft.lib.gui.context.gui.GUIClickContext;
import cn.ycraft.lib.gui.context.gui.GUIDragContext;
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
        GUIContext guiContext = null;
        if (ContextUtil.isClickContext(event)) {
            GUIClickContext clickContext = ContextUtil.toClickContext(event, gui);
            if (clickContext != null) {
                guiContext = clickContext;
                if (clickContext != null) {
                    // todo if button
                    GUIButton button = gui.getButton(clickContext.rawSlot());
                    GUIFrame frame = null; //todo
                    if (button != null) {
                        guiContext = new SimpleButtonClickContext(
                                event, gui, guiContext.rawSlot(), guiContext.getCursor(),
                                ((GUIClickContext) guiContext).clickType(), guiContext.rawSlot(), button, frame
                        );
                    }
                }
            }
        } else if (ContextUtil.isDropContext(event)) {
            guiContext = ContextUtil.toDropContext(event, gui);

        } else if (ContextUtil.isSwapContext(event)) {
            guiContext = ContextUtil.toSwapContext(event, gui);
        } else {
            guiContext = null;
            System.out.println("Unknown click type: " + event.getClick());
        }
        if (guiContext != null) {
            gui.trigger(player, guiContext); // Trigger the click event
            if (guiContext instanceof CancellableContext && ((CancellableContext) guiContext).isCancelled()) {
                event.setCancelled(true);
            }
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
        if (dragContext.isCancelled()) {
            event.setCancelled(true);
        }
    }
}

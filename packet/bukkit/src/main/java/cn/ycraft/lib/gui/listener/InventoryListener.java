package cn.ycraft.lib.gui.listener;

import cn.ycraft.lib.gui.ChestGUI;
import cn.ycraft.lib.gui.click.reponse.ClickCancelResponse;
import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.component.GUIFrame;
import cn.ycraft.lib.gui.context.CancellableContext;
import cn.ycraft.lib.gui.context.GUIContext;
import cn.ycraft.lib.gui.context.SimpleButtonClickContext;
import cn.ycraft.lib.gui.context.gui.GUIClickContext;
import cn.ycraft.lib.gui.holder.ChestInventory;
import cn.ycraft.lib.gui.util.ContextUtil;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientCloseWindow;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class InventoryListener extends PacketListenerAbstract {
    private final ChestGUI gui;

    public InventoryListener(ChestGUI gui) {
        this.gui = gui;
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        User user = event.getUser();
        if (user == null) return;
        Player clicker = event.getPlayer();
        if (clicker == null) return;

        if (event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW) {
            WrapperPlayClientClickWindow clickWindow = new WrapperPlayClientClickWindow(event);
            if (clickWindow.getWindowId() != gui.inventory().windowId()) return; // Not for this GUI.
            if (!gui.isViewer(clicker)) return; // Not for this user

            GUIContext guiContext = null;
            if (ContextUtil.isClickContext(clickWindow)) {
                GUIClickContext clickContext = ContextUtil.toClickContext(event, gui, clickWindow);
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
            } else if (ContextUtil.isDragContext(clickWindow)) {
                guiContext = ContextUtil.toDragContext(event, gui, clickWindow);
            } else if (ContextUtil.isDropContext(clickWindow)) {
                guiContext = ContextUtil.toDropContext(event, gui, clickWindow);
            } else if (ContextUtil.isSwapContext(clickWindow)) {
                guiContext = ContextUtil.toSwapContext(event, gui, clickWindow);
            } else {
                Bukkit.getLogger().info("Unknown click type: " + clickWindow.getWindowClickType());
            }
            if (guiContext != null) {
                gui.trigger(clicker, guiContext); // Trigger the click event
                if (guiContext instanceof CancellableContext && ((CancellableContext) guiContext).isCancelled()) {
                    ClickCancelResponse.response(guiContext);
                }
            }

            event.setCancelled(true);// Cancel transfer to bukkit
        }
        if (event.getPacketType() == PacketType.Play.Client.CLOSE_WINDOW) {
            WrapperPlayClientCloseWindow closeWindow = new WrapperPlayClientCloseWindow(event);
            ChestInventory inventory = gui.inventory();
            if (inventory == null || inventory.windowId() != closeWindow.getWindowId()) {
                return;
            }
            inventory._removeViewer(user.getUUID());
            gui.close(clicker);
        }
    }
}

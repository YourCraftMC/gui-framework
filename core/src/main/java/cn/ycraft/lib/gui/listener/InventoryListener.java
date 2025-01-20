package cn.ycraft.lib.gui.listener;

import cn.ycraft.lib.gui.ChestGUI;
import cn.ycraft.lib.gui.click.ClickMeta;
import cn.ycraft.lib.gui.context.GUIClickContext;
import cn.ycraft.lib.gui.holder.ChestInventory;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientCloseWindow;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class InventoryListener extends PacketListenerAbstract {
    private final ChestGUI gui;

    public InventoryListener(ChestGUI gui) {
        this.gui = gui;
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        User user = event.getUser();
        if (user == null) return;
        Player clicker = Bukkit.getPlayer(user.getUUID());
        if (clicker == null) return;

        if (event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW) {
            WrapperPlayClientClickWindow clickWindow = new WrapperPlayClientClickWindow(event);
            if (clickWindow.getWindowId() != gui.inventory().windowId()) return; // Not for this GUI.
            if (!gui.isViewer(clicker)) return; // Not for this user

            GUIClickContext clickContext = new GUIClickContext(gui, user, clickWindow);
            gui.trigger(clicker, clickContext); // Trigger the click event

            boolean cancelled = clickContext.isCancelled();
            event.setCancelled(true);
        }
        if (event.getPacketType() == PacketType.Play.Client.CLOSE_WINDOW) {
            WrapperPlayClientCloseWindow closeWindow = new WrapperPlayClientCloseWindow(event);
            ChestInventory inventory = (ChestInventory) pool.getInventory(closeWindow.getWindowId());
            if (inventory == null) {
                return;
            }
            inventory.removeViewer(user.getUUID());
        }
    }

    private void handleClick(@NotNull User user, WrapperPlayClientClickWindow packet) {

    }
}

package cn.ycraft.lib.gui.listener;

import cn.ycraft.lib.gui.InventoryPoolImpl;
import cn.ycraft.lib.gui.click.ClickMeta;
import cn.ycraft.lib.gui.click.GuiClickRequest;
import cn.ycraft.lib.gui.click.InternalClickMeta;
import cn.ycraft.lib.gui.click.reponse.ClickCancelResponse;
import cn.ycraft.lib.gui.holder.ChestInventoryWrapper;
import cn.ycraft.lib.gui.holder.InventoryWrapper;
import cn.ycraft.lib.gui.util.PacketUtil;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientCloseWindow;

public class InventoryListener extends PacketListenerAbstract {
    private final InventoryPoolImpl pool;

    public InventoryListener(InventoryPoolImpl pool) {
        this.pool = pool;
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        User user = event.getUser();
        if (user == null) {
            return;
        }
        if (event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW) {
            WrapperPlayClientClickWindow clickWindow = new WrapperPlayClientClickWindow(event);
            InventoryWrapper<?> inventory = pool.getInventory(clickWindow.getWindowId());
            if (inventory == null) {
                return;
            }
            //todo uuid?
            //noinspection SuspiciousMethodCalls
            if (!inventory.viewers().contains(event.getPlayer())) {
                return;
            }

            GuiClickRequest request = PacketUtil.toRequest(clickWindow);
            request.setDefault(ClickMeta.INVENTORY_POOL, this.pool);
            request.setDefault(ClickMeta.INVENTORY, inventory);
            request.setDefault(InternalClickMeta.PACKET_EVENT, event);
            request.setDefault(InternalClickMeta.USER, user);

            //todo cancel click
            new ClickCancelResponse().response(request);
            event.setCancelled(true);
        }
        if (event.getPacketType() == PacketType.Play.Client.CLOSE_WINDOW) {
            WrapperPlayClientCloseWindow closeWindow = new WrapperPlayClientCloseWindow(event);
            ChestInventoryWrapper inventory = (ChestInventoryWrapper) pool.getInventory(closeWindow.getWindowId());
            if (inventory == null) {
                return;
            }
            inventory.removeViewer(user.getUUID());
        }
    }
}

package cn.ycraft.lib.gui.click.reponse;

import cn.ycraft.lib.gui.click.ClickMeta;
import cn.ycraft.lib.gui.click.ClickRequest;
import cn.ycraft.lib.gui.click.ClickResponse;
import cn.ycraft.lib.gui.click.InternalClickMeta;
import cn.ycraft.lib.gui.click.type.ClickType;
import cn.ycraft.lib.gui.holder.ChestInventoryWrapper;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSetSlot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ClickCancelResponse implements ClickResponse {

    @Override
    public void response(ClickRequest clickRequest) {
        ClickType clickType = clickRequest.meta(ClickMeta.CLICK_TYPE);
        if (clickType == null) {
            return;
        }
        System.out.println(clickType);
        switch (clickType) {
            case MIDDLE_CLICK:
            case LEFT_CLICK_SLOT:
            case RIGHT_CLICK_SLOT:
            case LEFT_CLICK_OUTSIDE_DROP_ALL:
            case RIGHT_CLICK_OUTSIDE_DROP_SINGLE:
            case CTRL_DROP:
            case DROP: {
                sendAll(clickRequest);
                break;
            }
            case SWAP_OFFHAND:
            case PICKUP_ALL:
            case SHIFT_LEFT:
            case SHIFT_RIGHT:
            case START_LEFT_DRAG:
            case START_MIDDLE_DRAG:
            case START_RIGHT_DRAG:
            case ADD_LEFT_SLOT_DRAG:
            case ADD_MIDDLE_SLOT_DRAG:
            case ADD_RIGHT_SLOT_DRAG:
            case END_LEFT_DRAG:
            case END_MIDDLE_DRAG:
            case END_RIGHT_DRAG:
            case DOUBLE_CLICK: {
                sendAll(clickRequest);
                Player player = Bukkit.getPlayer(clickRequest.meta(InternalClickMeta.USER).getUUID());
                if (player != null) {
                    player.updateInventory();
                }
                break;
            }
            default: {
                sendAll(clickRequest);
                Player player = Bukkit.getPlayer(clickRequest.meta(InternalClickMeta.USER).getUUID());
                if (player != null) {
                    player.updateInventory();
                }
            }
        }
    }

    private void sendAll(ClickRequest clickRequest) {
        ChestInventoryWrapper inventory = (ChestInventoryWrapper) clickRequest.meta(ClickMeta.INVENTORY);
        User user = clickRequest.meta(InternalClickMeta.USER);
        if (inventory != null && user != null) {
            sendAllItem(inventory, user);
//            Bukkit.getScheduler().runTaskLater(JavaPlugin.getProvidingPlugin(getClass()), () -> {
            sendCursor(inventory, user);
//            }, 10);
        }
    }

    private void sendAllItem(ChestInventoryWrapper inventory, User user) {
        for (int i = 0; i < inventory.type().size(); i++) {
            WrapperPlayServerSetSlot setSlot = inventory.setSlot(i);
            user.sendPacket(setSlot);
        }
    }

    private void sendCursor(ChestInventoryWrapper inventory, User user) {
        user.sendPacket(ChestInventoryWrapper.setCursor());
        user.sendPacket(inventory.legacyCursorItem());
    }
}

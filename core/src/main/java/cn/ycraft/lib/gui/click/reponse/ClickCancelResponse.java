package cn.ycraft.lib.gui.click.reponse;

import cn.ycraft.lib.gui.context.GUIContext;
import cn.ycraft.lib.gui.context.gui.GUIClickContext;
import cn.ycraft.lib.gui.context.gui.GUIDropContext;
import cn.ycraft.lib.gui.util.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.player.User;
import org.bukkit.entity.Player;

public class ClickCancelResponse {

    public static void response(GUIContext context) {
        if (context instanceof GUIClickContext || context instanceof GUIDropContext) {
            if (context instanceof GUIClickContext) {
                System.out.println("Click: " + ((GUIClickContext) context).clickType());
            } else {
                System.out.println("Drop: " + ((GUIDropContext) context).dropType());
            }
            sendAll(context);
        } else {
            System.out.println(context);
            sendAll(context);
            PacketReceiveEvent event = (PacketReceiveEvent) context.event();
            Player player = event.getPlayer();
            player.updateInventory();
        }
    }

    private static void sendAll(GUIContext context) {
        PacketReceiveEvent event = (PacketReceiveEvent) context.event();
        Player player = event.getPlayer();
        User user = event.getUser();
        context.gui().inventory().updateView(player);
        sendCursor(user);
    }


    private static void sendCursor(User user) {
        user.sendPacket(PacketUtil.setCursor(null));
        user.sendPacket(PacketUtil.legacyCursorItem(null));
    }
}

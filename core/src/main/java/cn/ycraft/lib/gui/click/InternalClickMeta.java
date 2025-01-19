package cn.ycraft.lib.gui.click;

import com.github.retrooper.packetevents.event.PacketEvent;
import com.github.retrooper.packetevents.protocol.player.User;

public interface InternalClickMeta {
    ClickMeta<PacketEvent> PACKET_EVENT = ClickMeta.of(PacketEvent.class);
    ClickMeta<User> USER = ClickMeta.of(User.class);
}

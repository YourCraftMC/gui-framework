package cn.ycraft.lib.gui.holder;

import com.github.retrooper.packetevents.manager.server.ServerVersion;

import java.util.concurrent.atomic.AtomicInteger;

public class WindowIdGenerator {
    // >=1.21.2 var int
    // else byte
    private int MIN_ID = 1;
    private int MAX_ID = 127;


    private final AtomicInteger current;

    public WindowIdGenerator(ServerVersion serverVersion) {
        if (serverVersion.isNewerThanOrEquals(ServerVersion.V_1_21_2)) {
            // var int
            MIN_ID = -2147483648;
            MAX_ID = 2147483647;
        }
        this.current = new AtomicInteger(MIN_ID);
    }

    public int nextId() {
        int next = current.incrementAndGet();
        if (next > MAX_ID) {
            current.set(MIN_ID);
        }
        return current.get();
    }
}

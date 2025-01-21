package cn.ycraft.lib.gui.context.gui;

import cn.ycraft.lib.gui.context.CancellableContext;
import cn.ycraft.lib.gui.context.GUIContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Arrays;

public interface GUISwapContext extends GUIContext, CancellableContext {

    /**
     * Swap type in minecraft.
     * <br> See <a href="https://minecraft.wiki/w/Minecraft_Wiki:Projects/wiki.vg_merge/Protocol#Click_Container">Click_Container</a>
     */
    enum SwapType {
        HOR_BAR_1(0), HOR_BAR_2(1), HOR_BAR_3(2),
        HOR_BAR_4(3), HOR_BAR_5(4), HOR_BAR_6(5),
        HOR_BAR_7(6), HOR_BAR_8(7), HOR_BAR_9(8),
        /**
         * Offhand swap key F, (only in player inventory)
         */
        OFFHAND(40), UNKNOWN(-1);

        private final int slot;

        SwapType(int slot) {
            this.slot = slot;
        }

        public int slot() {
            return slot;
        }
    }

    default @NotNull SwapType swapType() {
        return Arrays.stream(SwapType.values()).filter(t -> t.slot() == slot()).findFirst().orElse(SwapType.UNKNOWN);
    }

    int slot();

    /**
     * Button is used as the slot index, usually from 0 to 8.
     * <br> More than 8 is impossible in vanilla clients
     *
     * @return hotbar slot index
     */
    @Range(from = 0, to = 8)
    int hotbarSlot();

}

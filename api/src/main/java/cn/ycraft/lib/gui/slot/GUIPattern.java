package cn.ycraft.lib.gui.slot;

import java.util.List;

public interface GUIPattern {

    static GUIPattern of(String... pattern) {
        throw new UnsupportedOperationException();
    }

    List<GUISlot> mappings(char mark);


}

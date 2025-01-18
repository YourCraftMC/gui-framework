package cn.ycraft.lib.gui.component;

import cn.ycraft.lib.gui.GUI;
import org.jetbrains.annotations.NotNull;

public interface GUIComponent {

    void draw(@NotNull GUI<?> gui);

}

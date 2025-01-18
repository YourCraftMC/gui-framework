package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import org.jetbrains.annotations.NotNull;

public interface GUIContext {

    @NotNull GUI<?> source();

}

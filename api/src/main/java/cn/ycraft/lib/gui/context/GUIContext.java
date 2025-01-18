package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import org.jetbrains.annotations.NotNull;

public interface GUIContext<G extends GUI<?>> {

    @NotNull G source();

}

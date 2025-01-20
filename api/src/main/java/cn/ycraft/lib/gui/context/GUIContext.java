package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.GUI;
import org.jetbrains.annotations.NotNull;

public interface GUIContext {

    /**
     * @return The original event that triggered this context.
     */
    @NotNull Object event();

    /**
     * @return The GUI that this context is for.
     */
    @NotNull GUI<?> gui();

}

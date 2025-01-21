package cn.ycraft.lib.gui.context.button;

import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.component.GUIFrame;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ButtonContext {

    /**
     * Get the button of this context
     *
     * @return the button instance
     */
    @NotNull GUIButton button();

    /**
     * Get the frame of the button belongs to,
     * if the button is not in a frame, return null.
     *
     * @return the frame of this button
     */
    @Nullable GUIFrame frame();


}

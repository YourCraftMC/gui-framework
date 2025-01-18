package cn.ycraft.lib.gui.component.button;

import cn.ycraft.lib.gui.component.GUIButton;
import org.jetbrains.annotations.NotNull;

/**
 * A button that is linked (or mapped) to another button
 */
public interface LinkedButton extends GUIButton {

    @NotNull GUIButton source();

}

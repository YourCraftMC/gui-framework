package cn.ycraft.lib.gui.component.button;

import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.component.frame.PaginateFrame;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A button type that used to navigate between pages.
 */
public interface PaginateButton extends GUIButton {

    @NotNull List<PaginateFrame> linkedFrame();

    void link(@NotNull PaginateFrame frame);

    void unlink(@NotNull PaginateFrame frame);

}

package cn.ycraft.lib.gui.context;

import cn.ycraft.lib.gui.click.ClickRequest;
import org.jetbrains.annotations.NotNull;

public interface ButtonClickContext extends ButtonContext {

    @NotNull ClickRequest request();
}

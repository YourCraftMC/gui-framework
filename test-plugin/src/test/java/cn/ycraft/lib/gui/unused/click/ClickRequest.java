package cn.ycraft.lib.gui.unused.click;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ClickRequest {
    @Nullable
    <T> T meta(@NotNull ClickMeta<T> clickMeta);

    <T> void setMeta(@NotNull ClickMeta<T> meta, T value);

    boolean containMeta(@NotNull ClickMeta<?> meta);
}

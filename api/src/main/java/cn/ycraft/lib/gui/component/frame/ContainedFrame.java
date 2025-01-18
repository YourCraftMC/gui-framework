package cn.ycraft.lib.gui.component.frame;

import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.component.GUIFrame;
import cn.ycraft.lib.gui.component.GUIIcon;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public interface ContainedFrame extends GUIFrame {

    /**
     * @return The icons contained in this area
     */
    @NotNull
    List<GUIIcon> contents();

    void contents(@NotNull List<GUIButton> icon);

    default void contents(@NotNull GUIButton... icon) {
        contents(Arrays.asList(icon));
    }

    void add(@NotNull GUIIcon icon, @NotNull GUIIcon... icons);

    default void add(@NotNull ItemStack item, @NotNull ItemStack... items) {
        add(GUIIcon.icon(item));
        Arrays.stream(items).map(GUIIcon::icon).forEachOrdered(this::add);
    }

    void remove(@NotNull GUIIcon icon);

}

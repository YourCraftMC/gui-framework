package cn.ycraft.lib.gui.component;

import cn.ycraft.lib.gui.GUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Set;


public interface GUIFrame extends GUIComponent {

    @NotNull GUI<?> parent();

    /**
     * All slots that this area will display
     *
     * @return slots used to display this area
     */
    @UnmodifiableView
    @NotNull Set<Integer> displaySlots();

    default int displaySize() {
        return displaySlots().size();
    }

}

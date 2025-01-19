package cn.ycraft.lib.gui.component;

import cn.ycraft.lib.gui.GUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Set;


public interface GUIFrame {

    /**
     * The parent GUI of this frame belongs to.
     *
     * @return parent GUI
     */
    @NotNull GUI<?> parent();

    /**
     * Draw this frame to the parent GUI
     */
    void draw();

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

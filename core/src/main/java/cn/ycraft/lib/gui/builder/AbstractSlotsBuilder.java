package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.slot.GUISlot;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSlotsBuilder<R, SELF extends PreparedGUISlots<R, SELF>> implements PreparedGUISlots<R, SELF> {
    private final List<GUISlot> slots = new ArrayList<>();
    private final List<GUISlot> excluded = new ArrayList<>();

    @Override
    public @NotNull List<GUISlot> slots() {
        return this.slots;
    }

    @Override
    public @NotNull List<GUISlot> excluded() {
        return this.excluded;
    }

    @Override
    public <T extends GUISlot> SELF at(@NotNull List<T> slots) {
        this.slots.addAll(slots);
        return getSelf();
    }

    @Override
    public <T extends GUISlot> SELF exclude(@NotNull List<T> slots) {
        this.excluded.addAll(slots);
        return getSelf();
    }

    public abstract SELF getSelf();
}

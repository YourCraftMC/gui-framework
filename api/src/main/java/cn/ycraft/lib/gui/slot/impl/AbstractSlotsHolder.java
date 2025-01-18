package cn.ycraft.lib.gui.slot.impl;

import cn.ycraft.lib.gui.slot.GUISlot;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSlotsHolder<B extends AbstractSlotsHolder<B>> {


    protected final @NotNull List<GUISlot> slots;
    protected final @NotNull List<GUISlot> excluded;

    protected AbstractSlotsHolder() {
        this(new ArrayList<>());
    }

    protected AbstractSlotsHolder(@NotNull List<GUISlot> slots) {
        this(slots, new ArrayList<>());
    }

    protected AbstractSlotsHolder(@NotNull List<GUISlot> slots, @NotNull List<GUISlot> excluded) {
        this.slots = slots;
        this.excluded = excluded;
    }

    public abstract B getThis();

    public @NotNull List<GUISlot> slots() {
        return slots;
    }

    public @NotNull List<GUISlot> excluded() {
        return excluded;
    }

    public B add(@NotNull GUISlot slot) {
        slots.add(slot);
        return getThis();
    }

    public B exclude(@NotNull GUISlot slot) {
        excluded.add(slot);
        return getThis();
    }

}

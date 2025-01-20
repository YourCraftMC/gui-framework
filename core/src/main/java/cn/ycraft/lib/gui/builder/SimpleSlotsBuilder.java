package cn.ycraft.lib.gui.builder;

import org.jetbrains.annotations.NotNull;

public abstract class SimpleSlotsBuilder<R> extends AbstractSlotsBuilder<R, SimpleSlotsBuilder<R>> {
    @Override
    public @NotNull SimpleSlotsBuilder<R> getThis() {
        return this;
    }
}

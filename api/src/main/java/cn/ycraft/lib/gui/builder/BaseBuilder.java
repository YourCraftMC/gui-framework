package cn.ycraft.lib.gui.builder;

import org.jetbrains.annotations.NotNull;

public interface BaseBuilder<BACKWARDS, SELF extends BaseBuilder<BACKWARDS, SELF>> {

    @NotNull BACKWARDS commit();

    @NotNull SELF getThis();

}

package cn.ycraft.lib.gui.builder;

import org.jetbrains.annotations.NotNull;

public interface PreparedGUIBuilder<BACKWARDS, SELF extends PreparedGUIBuilder<BACKWARDS, SELF>> {

    @NotNull BACKWARDS commit();

    @NotNull SELF getThis();

}

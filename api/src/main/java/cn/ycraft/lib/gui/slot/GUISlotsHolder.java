package cn.ycraft.lib.gui.slot;

import cn.ycraft.lib.gui.GUI;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public interface GUISlotsHolder<B extends GUISlotsHolder<B>> {

    default SortedSet<Integer> indexes(@NotNull GUI<?> gui) {
        SortedSet<Integer> v = new TreeSet<>();
        slots().stream().map(s -> s.indexOf(gui)).forEach(v::add);
        excluded().stream().map(s -> s.indexOf(gui)).forEach(v::remove);
        return v;
    }

    @NotNull List<GUISlot> slots();

    @NotNull List<GUISlot> excluded();

    B at(@NotNull GUIPattern pattern, char mark);

    B at(@NotNull GUISlot slot);

    B at(int index);

    B at(int row, int column);

    B atPlayer(int index);

    B atPlayer(int row, int column);

    B range(int start, int end);

    B range(int fromRow, int fromColumn, int toRow, int toColumn);

    B rangePlayer(int start, int end);

    B exclude(@NotNull GUISlot slot);

    B exclude(int index);

    B exclude(int row, int column);

    B excludePlayer(int index);

    B excludePlayer(int row, int column);

    B excludeRange(int start, int end);

    B excludeRange(int fromRow, int fromColumn, int toRow, int toColumn);

    B excludeRangePlayer(int start, int end);

}

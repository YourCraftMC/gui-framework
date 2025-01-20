package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.slot.GUIPattern;
import cn.ycraft.lib.gui.slot.GUISlot;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public interface PreparedGUISlots<R, SELF extends PreparedGUISlots<R, SELF>> extends BaseBuilder<R, SELF> {


    @NotNull List<GUISlot> slots();

    @NotNull List<GUISlot> excluded();

    default SortedSet<Integer> indexes(@NotNull GUI<?> gui) {
        SortedSet<Integer> v = new TreeSet<>();
        slots().stream().map(s -> s.indexOf(gui)).forEach(v::add);
        excluded().stream().map(s -> s.indexOf(gui)).forEach(v::remove);
        return v;
    }

    <T extends GUISlot> SELF at(@NotNull List<T> slots);

    default SELF at(@NotNull GUIPattern pattern, char mark) {
        return at(pattern.mappings(mark));
    }

    default SELF at(char mark, @NotNull String... pattern) {
        return at(GUIPattern.of(pattern), mark);
    }

    default SELF at(@NotNull GUISlot... slots) {
        return at(Arrays.asList(slots));
    }

    default SELF at(int index) {
        return at(GUISlot.index(index));
    }

    default SELF at(int row, int column) {
        return at(GUISlot.point(row, column));
    }

    default SELF atPlayer(int index) {
        return at(GUISlot.player(index));
    }

    default SELF atPlayer(int row, int column) {
        return at(GUISlot.player(row, column));
    }

    default SELF range(int start, int end) {
        return at(GUISlot.indexRange(start, end));
    }

    default SELF range(int fromRow, int fromColumn, int toRow, int toColumn) {
        return at(GUISlot.pointRange(fromRow, fromColumn, toRow, toColumn));
    }

    default SELF rangePlayer(int start, int end) {
        return at(GUISlot.playerRange(start, end));
    }

    <T extends GUISlot> SELF exclude(@NotNull List<T> slots);

    default SELF exclude(@NotNull GUISlot... slots) {
        return exclude(Arrays.asList(slots));
    }

    default SELF exlucde(char mark, @NotNull GUIPattern pattern) {
        return exclude(pattern.mappings(mark));
    }

    default SELF exclude(char mark, @NotNull String... pattern) {
        return exlucde(mark, GUIPattern.of(pattern));
    }

    default SELF exclude(int index) {
        return exclude(GUISlot.index(index));
    }

    default SELF exclude(int row, int column) {
        return exclude(GUISlot.point(row, column));
    }

    default SELF excludePlayer(int index) {
        return exclude(GUISlot.player(index));
    }

    default SELF excludePlayer(int row, int column) {
        return exclude(GUISlot.player(row, column));
    }

    default SELF excludeRange(int start, int end) {
        return exclude(GUISlot.indexRange(start, end));
    }

    default SELF excludeRange(int fromRow, int fromColumn, int toRow, int toColumn) {
        return exclude(GUISlot.pointRange(fromRow, fromColumn, toRow, toColumn));
    }

    default SELF excludeRangePlayer(int start, int end) {
        return exclude(GUISlot.playerRange(start, end));
    }

}

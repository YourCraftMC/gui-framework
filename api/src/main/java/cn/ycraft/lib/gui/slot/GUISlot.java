package cn.ycraft.lib.gui.slot;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.holder.InventoryType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a slot in a GUI.
 */
@FunctionalInterface
public interface GUISlot {
    int indexOf(GUI<?> gui);

    static GUISlot typeIndex(Function<InventoryType<?>, Integer> function) {
        return gui -> function.apply(gui.inventory().type());
    }

    static PointedSlot point(int row, int column) {
        return new PointedSlot(row, column);
    }

    static GUISlot index(int index) {
        return gui -> index;
    }

    static GUISlot player(int index) {
        return typeIndex(t -> t.playerIndex(index));
    }

    static PointedSlot player(int row, int column) {
        return new PointedSlot(row, column) {
            @Override
            public int indexOf(GUI<?> gui) {
                return gui.inventory().type().playerIndex((row * 9) - (column == 9 ? 1 : 10 - column));
            }
        };
    }

    static List<GUISlot> indexes(int index, int... indexes) {
        List<GUISlot> collect = new ArrayList<>();
        collect.add(index(index));
        Arrays.stream(indexes).mapToObj(GUISlot::index).forEach(collect::add);
        return collect;
    }

    static List<GUISlot> indexRange(int start, int end) {
        return IntStream.rangeClosed(start, end).mapToObj(GUISlot::index).collect(Collectors.toList());
    }

    static List<GUISlot> playerRange(int start, int end) {
        return IntStream.rangeClosed(start, end).mapToObj(GUISlot::player).collect(Collectors.toList());
    }

    static List<PointedSlot> pointRange(int startRow, int startColumn, int endRow, int endColumn) {
        List<PointedSlot> collect = new ArrayList<>();
        for (int row = startRow; row <= endRow; row++) {
            for (int column = startColumn; column <= endColumn; column++) {
                collect.add(point(row, column));
            }
        }
        return collect;
    }

    static List<PointedSlot> playerRange(int startRow, int startColumn, int endRow, int endColumn) {
        List<PointedSlot> collect = new ArrayList<>();
        for (int row = startRow; row <= endRow; row++) {
            for (int column = startColumn; column <= endColumn; column++) {
                collect.add(player(row, column));
            }
        }
        return collect;
    }

    class PointedSlot implements GUISlot {
        private final int row;
        private final int column;

        public PointedSlot(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int row() {
            return row;
        }

        public int column() {
            return column;
        }

        @Override
        public int indexOf(GUI<?> gui) {
            return gui.inventory().type().holderIndex(row, column);
        }
    }


}

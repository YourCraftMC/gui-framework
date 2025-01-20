package cn.ycraft.lib.gui.slot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class GUIPattern {

    public static GUIPattern of(String... pattern) {
        return new GUIPattern(Arrays.asList(pattern));
    }

    protected List<String> pattern;

    public GUIPattern(List<String> pattern) {
        this.pattern = pattern;
    }

    /**
     * Mapping the pattern to slots
     *
     * @param mark The character that represents a slot
     * @return A list of GUISlot objects, each object represents a slot in the pattern
     */
    public @NotNull List<GUISlot> mappings(char mark) {
        return mappings(mark, GUISlot::point);
    }

    /**
     * Mapping the pattern to slots
     *
     * @param mark The character that represents a slot
     * @return A list of GUISlot objects, each object represents a slot in the pattern
     */
    public @NotNull List<GUISlot> mappings(char mark, BiFunction<Integer, Integer, GUISlot> mapper) {
        List<GUISlot> slots = new ArrayList<>();
        for (int row = 0; row < pattern.size(); row++) {
            String line = pattern.get(row).replace(" ", ""); // Remove spaces
            for (int column = 0; column < line.length(); column++) {
                if (line.charAt(column) == mark) {
                    slots.add(mapper.apply(row, column));
                }
            }
        }
        return slots;
    }

}

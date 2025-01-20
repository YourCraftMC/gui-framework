package cn.ycraft.lib.gui.data;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Statements for GUI instance, used for storing temporary data in a single GUI.
 * <br> Those statements are shared in a same GUI instance.
 */
public class GUIStatements {

    protected final @NotNull Map<String, Object> values = new ConcurrentHashMap<>();

    public @NotNull Map<String, Object> values() {
        return values;
    }

    public boolean contains(@NotNull String key) {
        return values.containsKey(key);
    }

    public @NotNull Object get(@NotNull String key) {
        return values.get(key);
    }

    @SuppressWarnings("unchecked")
    public @NotNull <T> T get(@NotNull String key, @NotNull T defaultValue) {
        return contains(key) ? get(key, (Class<? extends T>) defaultValue.getClass()) : defaultValue;
    }

    public @NotNull <T> T get(@NotNull String key, @NotNull Class<T> clazz) {
        return clazz.cast(get(key));
    }

    public void set(@NotNull String key, @NotNull Object value) {
        values.put(key, value);
    }

    public void remove(@NotNull String key) {
        values.remove(key);
    }

    public void clear() {
        values.clear();
    }

    public void set(@NotNull Map<String, Object> values) {
        this.values.putAll(values);
    }

}

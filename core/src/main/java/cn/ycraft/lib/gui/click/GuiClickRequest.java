package cn.ycraft.lib.gui.click;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class GuiClickRequest implements ClickRequest {
    private final Map<ClickMeta<?>, Object> metaMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T> T meta(@NotNull ClickMeta<T> clickMeta) {
        Object object = metaMap.get(clickMeta);
        if (object == null) {
            return null;
        }
        if (!clickMeta.metaClass().isInstance(object)) {
            return null;
        }
        return (T) object;
    }

    @Override
    public <T> void setMeta(@NotNull ClickMeta<T> meta, T value) {
        if (!meta.mutable()) {
            throw new UnsupportedOperationException("Cannot set immutable meta");
        }
        this.metaMap.put(meta, value);
    }

    @Override
    public boolean containMeta(@NotNull ClickMeta<?> meta) {
        return this.metaMap.containsKey(meta);
    }

    public <T> void setDefault(ClickMeta<T> meta, T value) {
        this.metaMap.put(meta, value);
    }
}

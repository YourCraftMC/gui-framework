package cn.ycraft.lib.gui;

import cn.ycraft.lib.gui.builder.*;
import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.component.GUIFrame;
import cn.ycraft.lib.gui.component.GUIIcon;
import cn.ycraft.lib.gui.data.GUIStatements;
import cn.ycraft.lib.gui.holder.ChestInventoryWrapper;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;

public class ChestGui implements GUI<ChestInventoryWrapper> {
    private final GUIStatements flags = new GUIStatements();
    private final ChestInventoryWrapper wrapper;

    private final List<GUIFrame> frames = new ArrayList<>();
    private final SortedMap<Integer, GUIIcon> icons = new TreeMap<>();
    private final SortedMap<Integer, GUIButton> buttons = new TreeMap<>();

    public ChestGui(ChestInventoryWrapper wrapper) {
        this.wrapper = wrapper;
    }

    private void updateItems() {
        for (Map.Entry<Integer, GUIIcon> entry : this.icons.entrySet()) {
            this.wrapper.set(entry.getKey(), entry.getValue().item());
        }
        for (Map.Entry<Integer, GUIButton> entry : this.buttons.entrySet()) {
            this.wrapper.set(entry.getKey(), entry.getValue().item());
        }
    }

    @Override
    public ChestInventoryWrapper inventory() {
        return this.wrapper;
    }

    @Override
    public @NotNull GUIStatements flags() {
        return this.flags;
    }

    @Override
    public List<GUIFrame> frames() {
        return Collections.unmodifiableList(this.frames);
    }

    @Override
    public @Unmodifiable @NotNull SortedMap<Integer, GUIIcon> icons() {
        return Collections.unmodifiableSortedMap(this.icons);
    }

    @Override
    public void open(Player player) {
        wrapper.open(player);
    }

    @Override
    public void close(Player player) {
        wrapper.close(player);
    }

    @Override
    public void update(@NotNull GUIIcon icon) {
        //todo
        wrapper.updateView();
    }

    @Override
    public void update(@NotNull GUIFrame frame) {
        frame.draw();
    }

    @Override
    public void title(@NotNull String title) {
        wrapper.title(title);
    }

    @Override
    public PreparedGUIIcon<GUI<ChestInventoryWrapper>, ?> icon() {
        return new SimpleIconBuilder<GUI<ChestInventoryWrapper>>() {
            @Override
            public @NotNull GUI<ChestInventoryWrapper> commit() {
                GUIIcon icon = build();
                for (Integer index : indexes(ChestGui.this)) {
                    ChestGui.this.icons.put(index, icon.clone());
                }
                updateItems();
                return ChestGui.this;
            }
        };
    }

    @Override
    public PreparedGUIButton<GUI<ChestInventoryWrapper>, ?> button() {
        return new SimpleButtonBuilder<GUI<ChestInventoryWrapper>>() {
            @Override
            public @NotNull GUI<ChestInventoryWrapper> commit() {
                @NotNull GUIButton button = build();
                for (Integer index : indexes(ChestGui.this)) {
                    ChestGui.this.buttons.put(index, button);
                }
                updateItems();
                return ChestGui.this;
            }
        };
    }

    @Override
    public PreparedGUISlots<GUI<ChestInventoryWrapper>, ?> remove() {
        return new SimpleSlotsBuilder<GUI<ChestInventoryWrapper>>() {
            @Override
            public @NotNull GUI<ChestInventoryWrapper> commit() {
                for (int index : indexes(ChestGui.this)) {
                    ChestGui.this.icons.remove(index);
                    ChestGui.this.frames.forEach(frame -> frames.remove(index));
                }
                updateItems();
                return ChestGui.this;
            }
        };
    }

    @Override
    public void remove(@NotNull GUIIcon icon) {
        this.icons.entrySet().removeIf(entry -> entry.getValue().equals(icon));
    }

    @Override
    public void remove(@NotNull GUIFrame frame) {
        this.frames.remove(frame);
    }

    @Override
    public void put(@NotNull GUIFrame area) {
        this.frames.add(area);
    }

    @Override
    public <T extends GUIIcon> PreparedGUISlots<GUI<ChestInventoryWrapper>, ?> put(@NotNull T icon) {
        return new SimpleSlotsBuilder<GUI<ChestInventoryWrapper>>() {
            @Override
            public @NotNull GUI<ChestInventoryWrapper> commit() {
                for (Integer index : indexes(ChestGui.this)) {
                    ChestGui.this.icons.put(index, icon);
                }
                return ChestGui.this;
            }
        };
    }
}

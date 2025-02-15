package cn.ycraft.lib.gui;

import cn.ycraft.lib.gui.builder.*;
import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.component.GUIFrame;
import cn.ycraft.lib.gui.component.GUIIcon;
import cn.ycraft.lib.gui.context.GUIContext;
import cn.ycraft.lib.gui.context.SimpleGUICloseContext;
import cn.ycraft.lib.gui.context.SimpleGUIOpenContext;
import cn.ycraft.lib.gui.context.button.ButtonContext;
import cn.ycraft.lib.gui.data.GUIStatements;
import cn.ycraft.lib.gui.holder.AbstractChestInventory;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;

public abstract class AbstractChestGUI<W extends AbstractChestInventory<?>> implements GUI<W> {


    protected final @NotNull W inventory;
    protected final GUIStatements flags = new GUIStatements();

    protected final List<GUIFrame> frames = new ArrayList<>();
    protected final SortedMap<Integer, GUIIcon> icons = new TreeMap<>();
    protected final SortedMap<Integer, GUIButton> buttons = new TreeMap<>();

    public AbstractChestGUI(@NotNull W wrapper) {
        this.inventory = wrapper;
    }

    protected void updateItems() {
        for (Map.Entry<Integer, GUIIcon> entry : this.icons.entrySet()) {
            this.inventory.set(entry.getKey(), entry.getValue().item());
        }
        for (Map.Entry<Integer, GUIButton> entry : this.buttons.entrySet()) {
            this.inventory.set(entry.getKey(), entry.getValue().item());
        }
    }

    protected abstract void activeListeners();

    protected abstract void deactiveListeners();

    @Override
    public W inventory() {
        return this.inventory;
    }

    @Override
    public @NotNull GUIStatements flags() {
        return this.flags;
    }

    @Override
    public List<GUIFrame> frames() {
        return this.frames;
    }

    @Override
    public @Unmodifiable @NotNull SortedMap<Integer, GUIIcon> icons() {
        return Collections.unmodifiableSortedMap(this.icons);
    }

    @Override
    public void open(Player player) {
        SimpleGUIOpenContext openContext = new SimpleGUIOpenContext(null, this, -1, null);
        trigger(player, openContext);
        if (openContext.isCancelled()) {
            return;
        }
        this.inventory.open(player);
        activeListeners();
    }

    @Override
    public void close(Player player) {
        if (isViewer(player)) {
            trigger(player, new SimpleGUICloseContext(null, this));
        }
        this.inventory.close(player);
        if (this.inventory.viewers().isEmpty()) {
            deactiveListeners();
        }
    }

    @Override
    public void update(@NotNull GUIIcon icon) {
        //todo
        updateItems();
        inventory.updateView();
    }

    @Override
    public void update(@NotNull GUIFrame frame) {
        frame.draw();
    }

    @Override
    public void trigger(@NotNull Player viewer, @NotNull GUIContext context) {
        for (Map.Entry<Integer, GUIButton> entry : buttons.entrySet()) {
            if (context instanceof ButtonContext) {
                entry.getValue().trigger(viewer, (ButtonContext) context);
            }
        }
    }


    @Override
    public PreparedGUIIcon<GUI<W>, ?> icon() {
        return new SimpleIconBuilder<GUI<W>>() {
            @Override
            public @NotNull GUI<W> commit() {
                GUIIcon icon = build();
                for (Integer index : indexes(AbstractChestGUI.this)) {
                    AbstractChestGUI.this.icons.put(index, icon.clone());
                }
                updateItems();
                return AbstractChestGUI.this;
            }
        };
    }

    @Override
    public PreparedGUIButton<GUI<W>, ?> button() {
        return new SimpleButtonBuilder<GUI<W>>() {
            @Override
            public @NotNull GUI<W> commit() {
                @NotNull GUIButton button = build();
                for (Integer index : indexes(AbstractChestGUI.this)) {
                    AbstractChestGUI.this.buttons.put(index, button);
                }
                updateItems();
                return AbstractChestGUI.this;
            }
        };
    }

    @Override
    public PreparedGUISlots<GUI<W>, ?> remove() {
        return new SimpleSlotsBuilder<GUI<W>>() {
            @Override
            public @NotNull GUI<W> commit() {
                for (int index : indexes(AbstractChestGUI.this)) {
                    AbstractChestGUI.this.icons.remove(index);
                    AbstractChestGUI.this.frames.forEach(frame -> frames.remove(index));
                }
                updateItems();
                return AbstractChestGUI.this;
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
    public <T extends GUIIcon> PreparedGUISlots<GUI<W>, ?> put(@NotNull T icon) {
        return new SimpleSlotsBuilder<GUI<W>>() {
            @Override
            public @NotNull GUI<W> commit() {
                for (Integer index : indexes(AbstractChestGUI.this)) {
                    AbstractChestGUI.this.icons.put(index, icon);
                }
                return AbstractChestGUI.this;
            }
        };
    }

    public GUIButton getButton(int index) {
        return buttons.get(index);
    }
}

package cn.ycraft.lib.gui;

import cn.ycraft.lib.gui.builder.*;
import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.component.GUIFrame;
import cn.ycraft.lib.gui.component.GUIIcon;
import cn.ycraft.lib.gui.context.GUIContext;
import cn.ycraft.lib.gui.context.button.ButtonContext;
import cn.ycraft.lib.gui.data.GUIStatements;
import cn.ycraft.lib.gui.holder.ChestInventory;
import cn.ycraft.lib.gui.holder.ChestInventoryType;
import cn.ycraft.lib.gui.listener.InventoryListener;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;

public class ChestGUI implements GUI<ChestInventory> {

    public enum Rows {
        ONE(1), TWO(2), THREE(3),
        FOUR(4), FIVE(5), SIX(6);

        private final int row;

        private final ChestInventoryType type;

        Rows(int row) {
            this.row = row;
            this.type = new ChestInventoryType(row);
        }

        public int row() {
            return this.row;
        }

        public ChestInventoryType type() {
            return this.type;
        }

    }

    private final @NotNull GUIController controller;

    private final @NotNull ChestInventory inventory;
    private final GUIStatements flags = new GUIStatements();

    private final List<GUIFrame> frames = new ArrayList<>();
    private final SortedMap<Integer, GUIIcon> icons = new TreeMap<>();
    private final SortedMap<Integer, GUIButton> buttons = new TreeMap<>();
    private InventoryListener listener = null;

    public ChestGUI(@NotNull GUIController controller, @NotNull Rows type) {
        this.controller = controller;
        this.inventory = type.type.create();
    }

    private void updateItems() {
        for (Map.Entry<Integer, GUIIcon> entry : this.icons.entrySet()) {
            this.inventory.set(entry.getKey(), entry.getValue().item());
        }
        for (Map.Entry<Integer, GUIButton> entry : this.buttons.entrySet()) {
            this.inventory.set(entry.getKey(), entry.getValue().item());
        }
    }

    private void activeListeners() {
        if (this.listener == null) {
            this.listener = new InventoryListener(this);
            this.controller.packetEvents().getEventManager().registerListener(this.listener);
        }
    }

    private void deactiveListeners() {
        if (this.listener == null) return;
        this.controller.packetEvents().getEventManager().unregisterListener(this.listener);
        this.listener = null;
    }

    public GUIButton getButton(int index) {
        return buttons.get(index);
    }

    @Override
    public ChestInventory inventory() {
        return this.inventory;
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
        this.inventory.open(player);
        this.controller.setOpenedGUI(player.getUniqueId(), this);
        activeListeners();
    }

    @Override
    public void close(Player player) {
        this.inventory.close(player);
        this.controller.removeOpenedGUI(player.getUniqueId());
        if (this.inventory.viewers().isEmpty()) {
            deactiveListeners();
        }
    }

    @Override
    public void update(@NotNull GUIIcon icon) {
        //todo
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
    public PreparedGUIIcon<GUI<ChestInventory>, ?> icon() {
        return new SimpleIconBuilder<GUI<ChestInventory>>() {
            @Override
            public @NotNull GUI<ChestInventory> commit() {
                GUIIcon icon = build();
                for (Integer index : indexes(ChestGUI.this)) {
                    ChestGUI.this.icons.put(index, icon.clone());
                }
                updateItems();
                return ChestGUI.this;
            }
        };
    }

    @Override
    public PreparedGUIButton<GUI<ChestInventory>, ?> button() {
        return new SimpleButtonBuilder<GUI<ChestInventory>>() {
            @Override
            public @NotNull GUI<ChestInventory> commit() {
                @NotNull GUIButton button = build();
                for (Integer index : indexes(ChestGUI.this)) {
                    ChestGUI.this.buttons.put(index, button);
                }
                updateItems();
                return ChestGUI.this;
            }
        };
    }

    @Override
    public PreparedGUISlots<GUI<ChestInventory>, ?> remove() {
        return new SimpleSlotsBuilder<GUI<ChestInventory>>() {
            @Override
            public @NotNull GUI<ChestInventory> commit() {
                for (int index : indexes(ChestGUI.this)) {
                    ChestGUI.this.icons.remove(index);
                    ChestGUI.this.frames.forEach(frame -> frames.remove(index));
                }
                updateItems();
                return ChestGUI.this;
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
    public <T extends GUIIcon> PreparedGUISlots<GUI<ChestInventory>, ?> put(@NotNull T icon) {
        return new SimpleSlotsBuilder<GUI<ChestInventory>>() {
            @Override
            public @NotNull GUI<ChestInventory> commit() {
                for (Integer index : indexes(ChestGUI.this)) {
                    ChestGUI.this.icons.put(index, icon);
                }
                return ChestGUI.this;
            }
        };
    }
}

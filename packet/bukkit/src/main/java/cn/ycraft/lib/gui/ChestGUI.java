package cn.ycraft.lib.gui;

import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.holder.ChestInventory;
import cn.ycraft.lib.gui.holder.ChestInventoryType;
import cn.ycraft.lib.gui.listener.InventoryListener;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChestGUI extends AbstractChestGUI<ChestInventory> {

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
    private InventoryListener listener = null;

    public ChestGUI(@NotNull GUIController controller, @NotNull Rows type) {
        super(type.type.create());
        this.controller = controller;
    }

    protected void activeListeners() {
        if (this.listener == null) {
            this.listener = new InventoryListener(this);
            this.controller.packetEvents().getEventManager().registerListener(this.listener);
        }
    }

    protected void deactiveListeners() {
        if (this.listener == null) return;
        this.controller.packetEvents().getEventManager().unregisterListener(this.listener);
        this.listener = null;
    }

    @Override
    public void open(Player player) {
        super.open(player);
        if (isViewer(player)) {
            this.controller.setOpenedGUI(player.getUniqueId(), this);
        }
    }

    @Override
    public void close(Player player) {
        super.close(player);
        this.controller.removeOpenedGUI(player.getUniqueId());
    }
}

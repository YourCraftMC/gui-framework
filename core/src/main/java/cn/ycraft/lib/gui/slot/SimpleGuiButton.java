package cn.ycraft.lib.gui.slot;

import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.context.button.ButtonContext;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class SimpleGuiButton implements GUIButton {
    private Supplier<ItemStack> item;
    private BiConsumer<Player, ButtonContext> handler;

    public SimpleGuiButton() {
    }

    public SimpleGuiButton(Supplier<ItemStack> item, BiConsumer<Player, ButtonContext> handler) {
        this.item = item;
        this.handler = handler;
    }

    @Override
    public void trigger(@NotNull Player player, @NotNull ButtonContext context) {
        if (this.handler != null) {
            this.handler.accept(player, context);
        }
    }

    @Override
    public @Nullable ItemStack item() {
        if (this.item != null) {
            return this.item.get();
        }
        return null;
    }

    @Override
    public void item(@NotNull Supplier<@Nullable ItemStack> supplier) {
        this.item = supplier;
    }
}

package cn.ycraft.lib.gui.component;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SimpleGuiIcon implements GUIIcon {
    private Supplier<ItemStack> item;

    public SimpleGuiIcon(Supplier<ItemStack> item) {
        this.item = item;
    }

    @Override
    public @Nullable ItemStack item() {
        return this.item.get();
    }

    @Override
    public void item(@NotNull Supplier<@Nullable ItemStack> supplier) {
        this.item = supplier;
    }
}

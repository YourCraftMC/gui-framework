package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.component.GUIIcon;
import cn.ycraft.lib.gui.component.SimpleGuiIcon;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public abstract class SimpleIconBuilder<R>
        extends AbstractSlotsBuilder<R, SimpleIconBuilder<R>>
        implements PreparedGUIIcon<R, SimpleIconBuilder<R>> {
    private @NotNull Supplier<ItemStack> item;

    public SimpleIconBuilder() {
        this(() -> null);
    }

    public SimpleIconBuilder(@NotNull Supplier<ItemStack> item) {
        this.item = item;
    }

    @Override
    public GUIIcon build() {
        return new SimpleGuiIcon(item);
    }

    @Override
    public SimpleIconBuilder<R> item(Supplier<ItemStack> supplier) {
        this.item = supplier;
        return getThis();
    }

    public @NotNull SimpleIconBuilder<R> getThis() {
        return this;
    }

}

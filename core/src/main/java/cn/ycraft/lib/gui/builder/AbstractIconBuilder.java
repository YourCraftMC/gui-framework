package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.component.GUIIcon;
import cn.ycraft.lib.gui.component.SimpleGuiIcon;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public abstract class AbstractIconBuilder<R>
        extends AbstractSlotsBuilder<R, AbstractIconBuilder<R>>
        implements PreparedGUIIcon<R, AbstractIconBuilder<R>> {
    private @NotNull Supplier<ItemStack> item;

    public AbstractIconBuilder() {
        this(() -> null);
    }

    public AbstractIconBuilder(@NotNull Supplier<ItemStack> item) {
        this.item = item;
    }

    @Override
    public GUIIcon build() {
        return new SimpleGuiIcon(item);
    }

    @Override
    public AbstractIconBuilder<R> item(Supplier<ItemStack> supplier) {
        this.item = supplier;
        return getThis();
    }

    public @NotNull AbstractIconBuilder<R> getThis() {
        return this;
    }

}

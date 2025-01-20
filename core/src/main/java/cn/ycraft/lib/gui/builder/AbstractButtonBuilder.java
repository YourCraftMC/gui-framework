package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.context.ButtonContext;
import cn.ycraft.lib.gui.slot.SimpleGuiButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public abstract class AbstractButtonBuilder<R> extends AbstractSlotsBuilder<R, AbstractButtonBuilder<R>>
        implements PreparedGUIButton<R, AbstractButtonBuilder<R>> {
    private @NotNull Supplier<ItemStack> item;
    private @NotNull BiConsumer<Player, ButtonContext> handle = (p, c) -> {
    };

    public AbstractButtonBuilder() {
        this(() -> null);
    }

    public AbstractButtonBuilder(@NotNull Supplier<ItemStack> item) {
        this.item = item;
    }

    @Override
    public @NotNull AbstractButtonBuilder<R> getThis() {
        return this;
    }

    @Override
    public @NotNull GUIButton build() {
        return new SimpleGuiButton(this.item, this.handle);
    }

    @Override
    public AbstractButtonBuilder<R> item(Supplier<ItemStack> supplier) {
        this.item = supplier;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C extends ButtonContext> AbstractButtonBuilder<R> handle(@NotNull Class<C> contextType, @NotNull BiConsumer<Player, C> handler) {
        this.handle = this.handle.andThen((p, c) -> {
            if (contextType.isInstance(c)) {
                handler.accept(p, (C) c);
            }
        });
        return this;
    }

    @Override
    public AbstractButtonBuilder<R> processActions(@NotNull BiConsumer<Player, ButtonContext> handler) {
        this.handle = handler;
        return this;
    }
}

package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.component.GUIIcon;
import cn.ycraft.lib.gui.component.SimpleGuiIcon;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public abstract class SimplePreparedGuiIcon<R> implements PreparedGUIIcon<R, SimplePreparedGuiIcon<R>> {
    protected Supplier<ItemStack> item;

    @Override
    public GUIIcon build() {
        return new SimpleGuiIcon(item);
    }

    @Override
    public SimplePreparedGuiIcon<R> item(Supplier<ItemStack> item) {
        this.item = item;
        return this;
    }
}

package cn.ycraft.lib.gui.builder;

import cn.ycraft.lib.gui.component.GUIIcon;
import cn.ycraft.lib.gui.component.SimpleGuiIcon;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public abstract class AbstractIconBuilder<R, SELF extends AbstractIconBuilder<R, SELF>> implements PreparedGUIIcon<R, SELF> {
    private Supplier<ItemStack> item;

    @Override
    public GUIIcon build() {
        return new SimpleGuiIcon(item);
    }

    @Override
    public SELF item(Supplier<ItemStack> supplier) {
        this.item = supplier;
        return self();
    }

    public abstract SELF self();
}

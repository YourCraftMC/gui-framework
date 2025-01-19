package cn.ycraft.lib.gui.test;

import cn.ycraft.lib.gui.InventoryPool;
import cn.ycraft.lib.gui.InventoryPoolImpl;
import cn.ycraft.lib.gui.holder.ChestInventory;
import cn.ycraft.lib.gui.holder.InventoryWrapper;
import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class GuiTestPlugin extends JavaPlugin implements Listener {

    private InventoryPool pool;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        PacketEvents.getAPI().init();

        this.pool = new InventoryPoolImpl(PacketEvents.getAPI());
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
        this.pool.closeAll();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            @NotNull InventoryWrapper<?> inventoryWrapper = pool.chest(6).create();
            inventoryWrapper.set(0, new ItemStack(Material.STONE));
            inventoryWrapper.set(1, new ItemStack(Material.STONE));
            inventoryWrapper.set(2, new ItemStack(Material.STONE));

            inventoryWrapper.open(player);
        }
        return true;
    }
}

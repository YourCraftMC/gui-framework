package cn.ycraft.plugin.gui.test;

import cn.ycraft.lib.gui.ChestGui;
import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.InventoryPool;
import cn.ycraft.lib.gui.InventoryPoolImpl;
import cn.ycraft.lib.gui.holder.ChestInventoryWrapper;
import cn.ycraft.lib.gui.slot.GUISlot;
import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

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

            ChestInventoryWrapper inventoryWrapper = (ChestInventoryWrapper) pool.chest(6).create();
            GUI<?> gui = new ChestGui(inventoryWrapper);
            gui.icon(new ItemStack(Material.STONE))
                    .at(0)
                    .at(GUISlot.point(2, 2))
                    .commit();
            gui.open(player);
        }


        return true;
    }
}

package cn.ycraft.plugin.gui.test;

import cn.ycraft.lib.gui.ChestGUI;
import cn.ycraft.lib.gui.GUIController;
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

    private GUIController controller;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        PacketEvents.getAPI().init();
        this.controller = new GUIController(PacketEvents.getAPI());
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
        this.controller.closeAll();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ChestGUI gui = new ChestGUI(controller, ChestGUI.Rows.SIX); // 后续可以做成builder形式
            gui.icon(new ItemStack(Material.STONE))
                    .at(0)
                    .at(GUISlot.point(2, 2))
                    .commit();

            gui.button()
                    .item(new ItemStack(Material.DIAMOND))
                    .handleClick((clicker, context) -> {
//                        clicker.getInventory().addItem(new ItemStack(Material.DIAMOND));
                    })
                    .at(GUISlot.point(3, 1))
                    .commit();
            gui.open(player);
        }


        return true;
    }
}

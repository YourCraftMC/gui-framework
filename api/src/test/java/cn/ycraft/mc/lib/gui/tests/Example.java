package cn.ycraft.mc.lib.gui.tests;

import cn.ycraft.lib.gui.GUI;
import cn.ycraft.lib.gui.component.GUIButton;
import cn.ycraft.lib.gui.component.button.PaginateButton;
import cn.ycraft.lib.gui.component.frame.PaginateFrame;
import cn.ycraft.lib.gui.slot.GUIPattern;
import cn.ycraft.lib.gui.slot.GUISlot;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Example {


    void demo() {
        // 预先设计一些使用案例

        GUI<ChestInventory> gui = ChestGUI.create(ChestType.THREE_ROW);
        gui.title("Hello World"); // 设置GUI的标题

        // Component 是相对独立与GUI的一个存在，可作为组件置于多个不同的GUI中。
        GUIButton button = GUIButton.icon(() -> {
            // Button 的物品创建不应该涉及到玩家的信息。
            // 如果需要创建包含玩家信息的内容，则API的使用者应当于其他位置提供
            ItemStack icon = new ItemStack(Material.DIAMOND);
            icon.setAmount(50);
            return icon;
        }).handle((player, action, gui) -> {
            // 通过这个方法处理GUI的相关事件
            // 应当提供更多handle方法，以便于快捷处理特定类型的事件
            // 但其余方法都应当基于本方法存在
            // 该方法应当是可堆叠的，即可以多次调用，而后续的调用不会覆盖前面的调用
            // 因此，应当提供一个根方法，以便于覆盖调用

        }).processActions((player, action, gui) -> {
            // 该方法为最终处理事件的方法
            // 本质是一个 Consumer，且会覆盖先前设定的Consumer实例
        });
        gui.put(button).at(0, 0); // 将按钮放置在GUI的0,0位置

        // PagedArea 是一个分页区域，可以用于展示大量的内容，但是默认需要绑定到一个GUI中。
        PaginateFrame area = PaginateFrame.of(gui, 1, 2, 3, 4, 5, 6, 7);
        // 通过index的列表声明一个区域，以便于在后续的操作中引用，
        // 当然，我们可以提供许多区域的快捷创建方法，包括先前的a,b两点等方式

        area.add(button); // 将按钮放置在区域中

        // 为区域创建一个翻页按钮，其实next/prev/last/first方法本质上就是调用 handle 方法绑定了一些预先设计好的点击事件执行翻页
        // 这样的设计可以允许操作者额外的绑定事件，而不会影响到翻页的操作
        PaginateButton next = PaginateButton.icon(new ItemStack(Material.ARROW)).next(area).last(area);
        PaginateButton prev = PaginateButton.icon(new ItemStack(Material.ARROW)).prev(area).last(area);


        gui.put(next).atPlayer(0, 8).commit();    // 将翻页按钮放置在玩家背包的的 0 和 8 所在槽位


        // GUIPattern 是一个用于快速创建GUI格式的工具，其实本质上就是一个对（x,y) 的映射描述
        GUIPattern pattern = GUIPattern.of(
                "X X X X X X X X X",
                "X O O O O O O O X",
                "X O O O O O O O X",
                "X O O O O O O O X",
                "X X X X X X X X X",
                "X ← X X W X X → X"
        );
        PaginateFrame area2 = PaginateFrame.from(gui, pattern, "O");
        // 其实这个from的操作，就是给area增加了一个 slot 的集合，而slot包含的数据就是 (row,column)
        // 而实际显示的时候，只需要调用 gui 的 index(row,column) 方法，就可以获取到对应的slot的index

        GUISlot slot = GUISlot.point(2, 2); // 获取到pattern中的O所在的位置


        // 其余用法同理
        gui.put(button).at(pattern, 'W').commit(); // 将按钮放置在pattern对应的W所在的位置

    }

}

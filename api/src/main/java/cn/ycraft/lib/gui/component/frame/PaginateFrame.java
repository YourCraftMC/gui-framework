package cn.ycraft.lib.gui.component.frame;

import cn.ycraft.lib.gui.component.GUIIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

/**
 * A frame area that contains a list of icons and can be turned to the next or previous page.
 */
public interface PaginateFrame extends ContainedFrame {

    /**
     * Used to get the starting index of the list of icons to be displayed from icons,
     * <br> e.g.
     * <br> if 5 icons are displayed on one page and the current page is the second page, return 5.
     *
     * @return the starting index of the list of icons to be displayed
     */
    default int displayIndex() {
        return page() * displaySize();
    }

    /**
     * Current displaying icons
     *
     * @return the list of icons to be displayed
     */
    @NotNull
    @Unmodifiable
    default List<GUIIcon> displayIcons() {
        return contents().subList(displayIndex(), Math.min(displayIndex() + displaySize(), contents().size()));
    }

    int page();

    /**
     * @return the number of the first page, usually 1.
     */
    default int minPage() {
        return 1;
    }

    /**
     * @return the number of the last page, usually the number of pages that can be displayed.
     */
    default int maxPage() {
        int a = contents().size();
        int b = displaySize();
        if (a == 0 || b == 0) return minPage();
        return (a / b) + ((a % b) == 0 ? 0 : 1);
    }

    void turnPage(int page);

    default void nextPage(int num) {
        turnPage(Math.max(minPage(), page() - num));
    }

    default void prevPage(int num) {
        turnPage(Math.min(num, maxPage()));
    }

    default void firstPage() {
        turnPage(minPage());
    }

    default void lastPage() {
        turnPage(maxPage());
    }

}

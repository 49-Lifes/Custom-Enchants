package info.preva1l.customenchants.utils.guis;

import info.preva1l.customenchants.utils.Text;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class PaginatedFastInv extends FastInv {
    protected final Player player;
    private final List<PaginatedItem> paginatedItems = new ArrayList<>();
    protected int page = 0;
    protected int index = 0;
    private List<Integer> paginationMappings;

    protected PaginatedFastInv(int size, @NotNull String title, @NotNull Player player) {
        super(size, title);
        this.player = player;
        this.paginationMappings = List.of(
                10, 11, 12, 13, 14, 15, 16, 19, 20,
                21, 22, 23, 24, 25, 28, 29, 30,
                31, 32, 33, 34);
    }

    protected PaginatedFastInv(int size, @NotNull String title, @NotNull Player player, @NotNull List<Integer> paginationMappings) {
        super(size, title);
        this.player = player;
        this.paginationMappings = paginationMappings;
    }

    protected void setPaginationMappings(List<Integer> list) {
        this.paginationMappings = list;
    }

    protected void nextPage() {
        if (paginatedItems == null || paginatedItems.size() < index + 1) {
            return;
        }
        page++;
        populatePage();
        addPaginationControls();
    }

    protected void previousPage() {
        if (page == 0) {
            return;
        }
        page--;
        populatePage();
        addPaginationControls();
    }

    protected void populatePage() {
        int maxItemsPerPage = paginationMappings.size();
        boolean empty = paginatedItems == null || paginatedItems.isEmpty();
        for (int i = 0; i < maxItemsPerPage; i++) {
            removeItem(paginationMappings.get(i));
            if (empty) continue;
            index = maxItemsPerPage * page + i;
            if (index >= paginatedItems.size()) continue;
            PaginatedItem item = paginatedItems.get(index);
            setItem(paginationMappings.get(i), item.itemStack(), item.eventConsumer());
        }
        if (empty) {
            paginationEmpty();
        }
    }

    protected void updatePagination() {
        paginatedItems.clear();
        fillPaginationItems();
        populatePage();
        addPaginationControls();
    }

    protected void paginationEmpty() {
        setItems(new int[]{23},
                new ItemBuilder(Material.BARRIER)
                        .name(Text.colorize("&c&lNo Enchants Found!"))
                        .build());
    }

    protected abstract void fillPaginationItems();

    protected abstract void addPaginationControls();

    protected void addPaginationItem(PaginatedItem item) {
        paginatedItems.add(item);
    }
}

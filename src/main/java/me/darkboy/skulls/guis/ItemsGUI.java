/*
    Class created by NotStxnks_
    Project: GrindSkulls
    Date: 02/02/2020
    Time: 10:32
*/

package me.darkboy.skulls.guis;

import me.darkboy.skulls.listeners.ChatListener;
import me.darkboy.skulls.system.inventory.ClickableItem;
import me.darkboy.skulls.system.inventory.OmegaInventory;
import me.darkboy.skulls.system.inventory.content.InventoryContents;
import me.darkboy.skulls.system.inventory.content.InventoryProvider;
import me.darkboy.skulls.system.util.items.ItemBuilder;
import me.darkboy.skulls.utils.XItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemsGUI implements InventoryProvider {

    public static final OmegaInventory inv = OmegaInventory.builder()
            .id("itemsGUI")
            .provider(new ItemsGUI())
            .size(6, 9)
            .title("Grind Skulls")
            .clickable(true)
            .build();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fillBorders(ClickableItem.empty(new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName("§3Grind§bSkulls").build()));
        contents.set(5, 8, ClickableItem.of(new ItemBuilder(Material.EMERALD_BLOCK).setDisplayName("§aDone").build(), e -> {

            putData(contents, player);

            ItemsGUI.inv.close(player);
            ChatListener.skullData.remove(player);
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }

    private void putData(InventoryContents contents, Player player) {
        for (int row = 1; row <= 4; row++) {
            for (int column = 1; column <= 7; column++) {
                if (contents.get(row, column).isPresent() && !contents.get(row, column).get().getItem().getType().equals(Material.AIR)) {
                    ItemStack item = contents.get(row, column).get().getItem();
                    XItemStack.saveItemStack(item, ChatListener.skullData.get(player), column + row);
                }
            }
        }
    }
}

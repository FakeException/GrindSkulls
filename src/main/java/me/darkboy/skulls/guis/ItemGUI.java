/*
    Class created by NotStxnks_
    Project: GrindSkulls
    Date: 08/02/2020
    Time: 13:11
*/

package me.darkboy.skulls.guis;

import me.darkboy.skulls.listeners.ChatListener;
import me.darkboy.skulls.system.util.items.ItemBuilder;
import me.darkboy.skulls.utils.XItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ItemGUI implements Listener {

    public Inventory inv = Bukkit.createInventory(null, 54, "GrindSkulls Items");

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("GrindSkulls Items")) {

            if (e.getCurrentItem() != null) {

                if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Grind§bSkulls")) {
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aDone")) {
                        e.setCancelled(true);
                        saveItems(p);
                        p.closeInventory();
                    }
                } else if (e.getCurrentItem().getType().equals(Material.AIR)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    public void openInventory(Player player) {
        fillBorders(new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName("§3Grind§bSkulls"));
        inv.setItem(53, new ItemBuilder(Material.EMERALD_BLOCK).setDisplayName("§aDone").build());
        player.openInventory(inv);
    }

    private void saveItems(Player player) {
        for (int row = 1; row <= 4; row++) {
            for (int column = 1; column <= 7; column++) {
                int slot = 9 * row + column;
                if (inv.getItem(slot) != null && !Objects.requireNonNull(inv.getItem(slot)).getType().equals(Material.AIR)) {
                    ItemStack item = inv.getItem(slot);
                    if (item != null) {
                        XItemStack.saveItemStack(item, ChatListener.skullData.get(player), column + row);
                    }
                }
            }
        }
    }

    private void fillRect(ItemBuilder item) {
        for(int row = 0; row <= 5; row++) {
            for(int column = 0; column <= 8; column++) {
                if(row != 0 && row != 5 && column != 0 && column != 8)
                    continue;

                inv.setItem(9 * row + column, item.build());
            }
        }
    }

    private void fillBorders(ItemBuilder item) {
        fillRect(item);
    }
}

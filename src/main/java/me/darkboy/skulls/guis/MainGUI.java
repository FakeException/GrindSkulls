/*
    Class created by NotStxnks_
    Project: GrindSkulls
    Date: 30/01/2020
    Time: 21:45
*/

package me.darkboy.skulls.guis;

import me.darkboy.skulls.system.inventory.ClickableItem;
import me.darkboy.skulls.system.inventory.OmegaInventory;
import me.darkboy.skulls.system.inventory.content.InventoryContents;
import me.darkboy.skulls.system.inventory.content.InventoryProvider;
import me.darkboy.skulls.system.util.ActionBar;
import me.darkboy.skulls.system.util.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class MainGUI implements InventoryProvider {

    public static final OmegaInventory inv = OmegaInventory.builder()
            .id("mainGUI")
            .provider(new MainGUI())
            .size(3, 9)
            .title("Grind Skulls")
            .build();

    public static HashSet<Player> playerChatData = new HashSet<>();
    public static HashSet<Player> playerValueChatData = new HashSet<>();
    public static HashSet<Player> playerSkinChatData = new HashSet<>();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.set(1, 2, ClickableItem.of(new ItemBuilder(Material.WHITE_WOOL).setDisplayName("§eDisplay Name").build(), e -> {
            playerChatData.add(player);
            MainGUI.inv.close(player);
            ActionBar.sendMessage("§eType in the chat a display name!", player);
        }));

        contents.set(1, 4, ClickableItem.of(new ItemBuilder(Material.WHITE_WOOL).setDisplayName("§eGrind Times").build(), e -> {
            playerValueChatData.add(player);
            MainGUI.inv.close(player);
            ActionBar.sendMessage("§eType in the chat a new value!", player);
        }));

        contents.set(1, 6, ClickableItem.of(new ItemBuilder(Material.WHITE_WOOL).setDisplayName("§eChange Skin").build(), e -> {
            playerSkinChatData.add(player);
            MainGUI.inv.close(player);
            ActionBar.sendMessage("§eType in the chat a new skin name!", player);
        }));

        contents.set(2, 8, ClickableItem.of(new ItemBuilder(Material.EMERALD_BLOCK).setDisplayName("§7Continue").build(), e -> {
            ItemGUI gui = new ItemGUI();
            gui.openInventory(player);
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}

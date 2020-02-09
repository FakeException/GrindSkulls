/*
    Class created by NotStxnks_
    Project: GrindSkulls
    Date: 31/01/2020
    Time: 16:15
*/

package me.darkboy.skulls.listeners;

import me.darkboy.skulls.guis.MainGUI;
import me.darkboy.skulls.utils.GrindSkullUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.HashMap;

public class ChatListener implements Listener {

    public static HashMap<Player, String> skullData = new HashMap<>();

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player player = e.getPlayer();
        if (MainGUI.playerChatData.contains(player)) {
            e.setCancelled(true);
            GrindSkullUtils.changeDisplayName(skullData.get(player), e.getMessage());
            player.sendMessage("§7Display name changed!");
            MainGUI.playerChatData.remove(player);
            MainGUI.inv.open(player);
        } else if (MainGUI.playerValueChatData.contains(player)) {
            e.setCancelled(true);
            try {
                GrindSkullUtils.changeValue(skullData.get(player), Integer.parseInt(e.getMessage()));
                player.sendMessage("§7Value changed!");
                MainGUI.playerValueChatData.remove(player);
                MainGUI.inv.open(player);
            } catch (Exception ex) {
                player.sendMessage("§7Please input a valid number.");
            }
        } else if (MainGUI.playerSkinChatData.contains(player)) {
            e.setCancelled(true);
            GrindSkullUtils.changeTexture(skullData.get(player), e.getMessage());
            player.sendMessage("§7Skin owner changed!");
            MainGUI.playerSkinChatData.remove(player);
            MainGUI.inv.open(player);
        }
    }
}

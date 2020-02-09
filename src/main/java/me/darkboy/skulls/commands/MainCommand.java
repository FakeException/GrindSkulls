/*
    Class created by NotStxnks_
    Project: GrindSkulls
    Date: 31/01/2020
    Time: 16:17
*/

package me.darkboy.skulls.commands;

import me.darkboy.skulls.GrindSkulls;
import me.darkboy.skulls.guis.MainGUI;
import me.darkboy.skulls.listeners.ChatListener;
import me.darkboy.skulls.skulls.GrindSkull;
import me.darkboy.skulls.system.register.command.OmegaCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand extends OmegaCommand {

    public MainCommand() {
        this.setAliases("grindskulls", "gs");
        this.setPermission("grindskulls.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage("§7---------------------");
            player.sendMessage("");
            player.sendMessage("§e/grindskulls create (Name)");
            player.sendMessage("§e/grindskulls edit (Name)");
            player.sendMessage("§e/grindskulls remove (Name)");
            player.sendMessage("");
            player.sendMessage("§7---------------------");

        } else {

            if (args.length == 2) {

                if (args[0].equalsIgnoreCase("create")) {
                    if (!GrindSkulls.getSkullManager().isRegistered(args[1])) {
                        GrindSkulls.getSkullManager().createSkull(new GrindSkull(args[1], args[1], "none", 200));
                        ChatListener.skullData.put(player, args[1].toLowerCase());
                        MainGUI.inv.open(player);
                    }
                }
            }
        }
    }
}

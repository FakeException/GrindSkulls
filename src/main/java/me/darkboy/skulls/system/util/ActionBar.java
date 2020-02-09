package me.darkboy.skulls.system.util;

import net.minecraft.server.v1_14_R1.ChatMessageType;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {

    public static void sendMessage(String message, Player... players) {
        IChatBaseComponent text = ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(text, ChatMessageType.GAME_INFO);
        for (Player player : players) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
        }
    }

    public static void removeActionBar(Player... players) {
        IChatBaseComponent text = ChatSerializer.a("{\"text\": \"" + "" + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(text, ChatMessageType.GAME_INFO);
        for (Player player : players) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(bar);
        }
    }
}
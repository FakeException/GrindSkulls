/*
    Class created by NotStxnks_
    Project: GrindSkulls
    Date: 31/01/2020
    Time: 16:29
*/

package me.darkboy.skulls.utils;

import me.darkboy.skulls.GrindSkulls;

public class GrindSkullUtils {

    public static void changeDisplayName(String grindSkull, String newName) {
        GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".displayName", newName);
        GrindSkulls.getConfiguration().saveConfig();
    }

    public static void changeTexture(String grindSkull, String newTexture) {
        GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".texture", newTexture);
        GrindSkulls.getConfiguration().saveConfig();
    }

    public static void changeValue(String grindSkull, int value) {
        GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".value", value);
        GrindSkulls.getConfiguration().saveConfig();
    }
}

package me.darkboy.skulls.skulls;

import me.darkboy.skulls.GrindSkulls;

import java.util.Collection;
import java.util.HashMap;

public class SkullManager {

    private final HashMap<String, GrindSkull> skulls = new HashMap<>();

    public void createSkull(GrindSkull grindSkull) {
        skulls.put(grindSkull.getName().toLowerCase(), grindSkull);

        GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull.getName().toLowerCase() + ".name", grindSkull.getName());
        GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull.getName().toLowerCase() + ".displayName", grindSkull.getDisplayName());
        GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull.getName().toLowerCase() + ".texture", grindSkull.getSkinTexture());
        GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull.getName().toLowerCase() + ".value", grindSkull.getGrindValue());
        GrindSkulls.getConfiguration().saveConfig();
    }

    public void unloadSkull(String grindSkull) {
        skulls.remove(grindSkull.toLowerCase());
    }

    public Collection<GrindSkull> getGrindSkulls() {
        return skulls.values();
    }

    public GrindSkull getGrindSkull(String grindSkull) {
        return skulls.get(grindSkull.toLowerCase());
    }

    public boolean isRegistered(String grindSkull) {
        return skulls.containsKey(grindSkull.toLowerCase());
    }
}
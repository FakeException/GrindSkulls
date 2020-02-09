/*
    Class created by NotStxnks_
    Project: GrindSkulls
    Date: 31/01/2020
    Time: 15:55
*/

package me.darkboy.skulls.skulls;

public class GrindSkull {

    private String name;
    private String displayName;
    private String skinTexture;
    private int grindValue;

    public GrindSkull(String name, String displayName, String skinTexture, int grindValue) {
        this.name = name;
        this.displayName = displayName;
        this.skinTexture = skinTexture;
        this.grindValue = grindValue;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSkinTexture() {
        return skinTexture;
    }

    public int getGrindValue() {
        return grindValue;
    }
}

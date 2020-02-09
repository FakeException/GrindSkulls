package me.darkboy.skulls.utils;

import com.google.common.base.Enums;
import com.google.common.base.Strings;
import me.darkboy.skulls.GrindSkulls;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.TropicalFish;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionEffect;

import java.util.*;

public class XItemStack {

    public static void saveItemStack(ItemStack item, String grindSkull, int index) {
        if (item.getItemMeta() != null) {
            ItemMeta meta = item.getItemMeta();

            if (meta.hasDisplayName())
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "name", meta.getDisplayName());
            if (item.getAmount() > 1)
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "amount", item.getAmount());
            if (meta instanceof Damageable) {
                Damageable damageable = (Damageable) meta;
                if (damageable.hasDamage())
                    GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "damage", damageable.getDamage());
            }
            GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "material", item.getType().toString());
            if (meta.hasCustomModelData())
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "custom-model", meta.getCustomModelData());
            if (meta.isUnbreakable())
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "unbreakable", true);
            if (meta.getItemFlags().size() != 0)
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "flags", meta.getItemFlags());
            if (meta.hasAttributeModifiers())
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "attributes", meta.getAttributeModifiers());
            if (meta.hasLore())
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "lore", meta.getLore());
            if (meta.getEnchants().size() != 0)
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "enchants", meta.getEnchants());
            if (XMaterial.supports(9) && meta.hasAttributeModifiers())
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "attributes", meta.getAttributeModifiers());

            if (meta instanceof SkullMeta) {
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "skull", ((SkullMeta) meta).getOwningPlayer().getUniqueId());
            } else if (meta instanceof BannerMeta) {
                BannerMeta banner = (BannerMeta) meta;
                List<String> patterns = new ArrayList<>();
                for (Pattern pattern : banner.getPatterns()) {
                    patterns.add(pattern.getColor() + " " + pattern.getPattern().getIdentifier());
                }
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "patterns", patterns);
            } else if (meta instanceof LeatherArmorMeta) {
                LeatherArmorMeta leather = (LeatherArmorMeta) meta;
                Color color = leather.getColor();
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "color", color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());
            } else if (meta instanceof PotionMeta) {
                PotionMeta potion = (PotionMeta) meta;
                List<String> effects = new ArrayList<>();
                for (PotionEffect effect : potion.getCustomEffects())
                    effects.add(effect.getType().getName() + " " + effect.getDuration() + " " + effect.getAmplifier());

                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "effects", effects);
            } else if (meta instanceof FireworkMeta) {
                FireworkMeta firework = (FireworkMeta) meta;
                GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "power", firework.getPower());
                int i = 0;

                for (FireworkEffect fw : firework.getEffects()) {
                    ConfigurationSection fwc = GrindSkulls.getConfiguration().getConfig().getConfigurationSection("firework." + i);
                    fwc.set("type", fw.getType().name());

                    List<String> colors = new ArrayList<>();
                    for (Color color : fw.getColors())
                        colors.add(color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());
                    fwc.set("colors", colors);

                    colors.clear();
                    for (Color color : fw.getFadeColors())
                        colors.add(color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());
                    fwc.set("fade-colors", colors);
                }

                //} else if (meta instanceof MapMeta) {
            } else if (XMaterial.supports(14)) {
                if (meta instanceof TropicalFishBucketMeta) {
                    TropicalFishBucketMeta tropical = (TropicalFishBucketMeta) meta;
                    GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "pattern", tropical.getPattern().name());
                    GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "color", tropical.getBodyColor().name());
                    GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "pattern-color", tropical.getPatternColor().name());
                } else if (meta instanceof SuspiciousStewMeta) {
                    SuspiciousStewMeta stew = (SuspiciousStewMeta) meta;
                    List<String> effects = new ArrayList<>();
                    for (PotionEffect effect : stew.getCustomEffects()) {
                        effects.add(effect.getType().getName() + " " + effect.getDuration() + " " + effect.getAmplifier());
                    }

                    GrindSkulls.getConfiguration().getConfig().set("skulls." + grindSkull + ".items." + index + "." + "effects", effects);
                }
            }
            GrindSkulls.getConfiguration().saveConfig();
        }
    }

    @SuppressWarnings("deprecation")
    public static ItemStack getItemStack(String grindSkull, int index) {
        // Material
        String material = GrindSkulls.getConfiguration().getConfig().getString("skulls." + grindSkull + ".items." + index + "." + "material");
        if (material == null) return null;
        Optional<XMaterial> matOpt = XMaterial.matchXMaterial(material);
        if (!matOpt.isPresent()) return null;

        // Build
        ItemStack item = matOpt.get().parseItem();
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();

        // Amount
        int amount = GrindSkulls.getConfiguration().getConfig().getInt("skulls." + grindSkull + ".items." + index + "." + "amount");
        if (amount > 1) item.setAmount(amount);

        // Durability - Damage
        if (XMaterial.isNewVersion()) {
            if (meta instanceof Damageable) {
                int damage = GrindSkulls.getConfiguration().getConfig().getInt("skulls." + grindSkull + ".items." + index + "." + "damage");
                if (damage > 0) ((Damageable) meta).setDamage(damage);
            }
        } else {
            int damage = GrindSkulls.getConfiguration().getConfig().getInt("skulls." + grindSkull + ".items." + index + "." + "damage");
            if (damage > 1) item.setDurability((short) damage);
        }

        // Special Items
        if (item.getType() == XMaterial.PLAYER_HEAD.parseMaterial()) {
            String skull = GrindSkulls.getConfiguration().getConfig().getString("skulls." + grindSkull + ".items." + index + "." + "skull");
            if (skull != null) SkullUtils.applySkin(meta, skull);
        } else if (meta instanceof BannerMeta) {
            BannerMeta banner = (BannerMeta) meta;

            for (String pattern : GrindSkulls.getConfiguration().getConfig().getStringList("skulls." + grindSkull + ".items." + index + "." + "patterns")) {
                String[] split = pattern.split("  +");
                if (split.length == 0) continue;
                DyeColor color = Enums.getIfPresent(DyeColor.class, split[0]).or(DyeColor.WHITE);
                PatternType type;

                if (split.length > 1) {
                    type = PatternType.getByIdentifier(split[1]);
                    if (type == null) type = Enums.getIfPresent(PatternType.class, split[1]).or(PatternType.BASE);
                } else {
                    type = PatternType.BASE;
                }

                banner.addPattern(new Pattern(color, type));
            }
        } else if (meta instanceof LeatherArmorMeta) {
            LeatherArmorMeta leather = (LeatherArmorMeta) meta;
            String colorStr = GrindSkulls.getConfiguration().getConfig().getString("skulls." + grindSkull + ".items." + index + "." + "color");
            if (colorStr != null) {
                leather.setColor(parseColor(colorStr));
            }
        } else if (meta instanceof PotionMeta) {
            PotionMeta potion = (PotionMeta) meta;
            for (String effects : GrindSkulls.getConfiguration().getConfig().getStringList("skulls." + grindSkull + ".items." + index + "." + "effects")) {
                PotionEffect effect = XPotion.parsePotionEffectFromString(effects);
                potion.addCustomEffect(effect, true);
            }
        } else if (meta instanceof FireworkMeta) {
            FireworkMeta firework = (FireworkMeta) meta;
            FireworkEffect.Builder builder = FireworkEffect.builder();
            for (String fws : GrindSkulls.getConfiguration().getConfig().getConfigurationSection("skulls." + grindSkull + ".items." + index + "." + "fireworks").getKeys(false)) {
                ConfigurationSection fw = GrindSkulls.getConfiguration().getConfig().getConfigurationSection("skulls." + grindSkull + ".items." + index + "." + "firework." + fws);

                firework.setPower(fw.getInt("power"));
                builder.flicker(fw.getBoolean("flicker"));
                builder.trail(fw.getBoolean("trail"));
                builder.with(Enums.getIfPresent(FireworkEffect.Type.class, fw.getString("type")).or(FireworkEffect.Type.STAR));

                List<Color> colors = new ArrayList<>();
                for (String colorStr : fw.getStringList("colors")) {
                    colors.add(parseColor(colorStr));
                }
                builder.withColor(colors);

                colors.clear();
                for (String colorStr : fw.getStringList("fade-colors")) {
                    colors.add(parseColor(colorStr));
                }
                builder.withFade(colors);

                firework.addEffect(builder.build());
            }
            //} else if (meta instanceof MapMeta) {
            // TODO This is a little bit too complicated.
            //MapMeta map = (MapMeta) meta;
        } else if (XMaterial.supports(14)) {
            if (meta instanceof TropicalFishBucketMeta) {
                TropicalFishBucketMeta tropical = (TropicalFishBucketMeta) meta;
                DyeColor color = Enums.getIfPresent(DyeColor.class, GrindSkulls.getConfiguration().getConfig().getString("skulls." + grindSkull + ".items." + index + "." + "color")).or(DyeColor.WHITE);
                DyeColor patternColor = Enums.getIfPresent(DyeColor.class, Objects.requireNonNull(GrindSkulls.getConfiguration().getConfig().getString("skulls." + grindSkull + ".items." + index + "." + "pattern-color"))).or(DyeColor.WHITE);
                TropicalFish.Pattern pattern = Enums.getIfPresent(TropicalFish.Pattern.class, Objects.requireNonNull(GrindSkulls.getConfiguration().getConfig().getString("skulls." + grindSkull + ".items." + index + "." + "pattern"))).or(TropicalFish.Pattern.BETTY);

                tropical.setBodyColor(color);
                tropical.setPatternColor(patternColor);
                tropical.setPattern(pattern);
            } else if (meta instanceof SuspiciousStewMeta) {
                SuspiciousStewMeta stew = (SuspiciousStewMeta) meta;
                for (String effects : GrindSkulls.getConfiguration().getConfig().getStringList("skulls." + grindSkull + ".items." + index + "." + "effects")) {
                    PotionEffect effect = XPotion.parsePotionEffectFromString(effects);
                    stew.addCustomEffect(Objects.requireNonNull(effect), true);
                }
            }
        }

        // Displayname
        String name = GrindSkulls.getConfiguration().getConfig().getString("skulls." + grindSkull + ".items." + index + "." + "name");
        if (name != null) {
            if (name.isEmpty()) name = " ";
            String translated = ChatColor.translateAlternateColorCodes('&', name);
            meta.setDisplayName(translated);
        }

        // Unbreakable
        if (XMaterial.supports(11)) meta.setUnbreakable(GrindSkulls.getConfiguration().getConfig().getBoolean("skulls." + grindSkull + ".items." + index + "." + "unbreakable"));

        // Custom Model Data
        if (XMaterial.supports(14)) {
            int modelData = GrindSkulls.getConfiguration().getConfig().getInt("skulls." + grindSkull + ".items." + index + "." + "model-data");
            if (modelData != 0) meta.setCustomModelData(modelData);
        }

        // Lore
        List<String> lores = GrindSkulls.getConfiguration().getConfig().getStringList("skulls." + grindSkull + ".items." + index + "." + "lore");
        if (!lores.isEmpty()) {
            ArrayList<String> translatedLore = new ArrayList<>();
            String lastColors = "";

            for (String lore : lores) {
                if (lore.isEmpty()) {
                    translatedLore.add(" ");
                    continue;
                }

                for (String singleLore : StringUtils.splitPreserveAllTokens(lore, '\n')) {
                    if (singleLore.isEmpty()) {
                        translatedLore.add(" ");
                        continue;
                    }
                    singleLore = lastColors + ChatColor.translateAlternateColorCodes('&', singleLore);
                    translatedLore.add(singleLore);

                    lastColors = ChatColor.getLastColors(singleLore);
                }
            }

            meta.setLore(translatedLore);
        }

        // Enchantments
        List<String> enchants = GrindSkulls.getConfiguration().getConfig().getStringList("skulls." + grindSkull + ".items." + index + "." + "enchants");
        for (String ench : enchants) {
            String[] parseEnchant = StringUtils.split(StringUtils.deleteWhitespace(ench), ',');
            Optional<XEnchantment> enchant = XEnchantment.matchXEnchantment(parseEnchant[0]);
            if (enchant.isPresent()) {
                int lvl = parseEnchant.length > 1 ? Integer.parseInt(parseEnchant[1]) : 1;
                meta.addEnchant(Objects.requireNonNull(enchant.get().parseEnchantment()), lvl, false);
            }
        }

        // Flags
        List<String> flags = GrindSkulls.getConfiguration().getConfig().getStringList("skulls." + grindSkull + ".items." + index + "." + "flags");
        for (String flag : flags) {
            if (flag.equalsIgnoreCase("all")) {
                meta.addItemFlags(ItemFlag.values());
                break;
            }

            ItemFlag itemFlag = ItemFlag.valueOf(flag.toUpperCase());
            meta.addItemFlags(itemFlag);
        }

        // Atrributes
        if (XMaterial.supports(9)) {
            ConfigurationSection attributes = GrindSkulls.getConfiguration().getConfig().getConfigurationSection("skulls." + grindSkull + ".items." + index + "." + "attributes");
            if (attributes != null) {
                for (String attribute : attributes.getKeys(false)) {
                    AttributeModifier modifier = new AttributeModifier(
                            UUID.randomUUID(),
                            attributes.getString("generic"),
                            attributes.getInt("amount"),
                            Enums.getIfPresent(AttributeModifier.Operation.class, attributes.getString("operation"))
                                    .or(AttributeModifier.Operation.ADD_NUMBER),
                            Enums.getIfPresent(EquipmentSlot.class, attributes.getString("slot")).or(EquipmentSlot.HAND));

                    meta.addAttributeModifier(Attribute.valueOf(attribute), modifier);
                }
            }
        }

        item.setItemMeta(meta);
        return item;
    }

    public static Color parseColor(String str) {
        if (Strings.isNullOrEmpty(str)) return Color.BLACK;
        String[] rgb = StringUtils.split(StringUtils.deleteWhitespace(str), ',');
        return Color.fromRGB(NumberUtils.toInt(rgb[0], 0), NumberUtils.toInt(rgb[1], 0), NumberUtils.toInt(rgb[1], 0));
    }
}
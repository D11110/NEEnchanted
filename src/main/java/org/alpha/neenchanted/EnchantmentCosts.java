package org.alpha.neenchanted;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentCosts {
    public static Map<Enchantment, Integer> getEnchantmentCosts(ItemStack item) {
        Map<Enchantment, Integer> enchantmentCosts = new HashMap<>();
        Map<Enchantment, Integer> enchantments = item.getEnchantments();
        for (Enchantment enchantment : enchantments.keySet()) {
            int maxLevel = enchantment.getMaxLevel();
            enchantmentCosts.put(enchantment, maxLevel);
        }
        return enchantmentCosts;
    }
}
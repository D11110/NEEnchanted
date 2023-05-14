package org.alpha.neenchanted;

import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public class Enchantments implements Listener {
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack leftItem = event.getInventory().getItem(0);
        ItemStack rightItem = event.getInventory().getItem(1);

        if (leftItem == null || rightItem == null) {
            return;
        }

        Map<Enchantment, Integer> leftEnchantments = leftItem.getEnchantments();
        Map<Enchantment, Integer> rightEnchantments = rightItem.getEnchantments();

        if (leftEnchantments.size() > 0 && rightEnchantments.size() > 0) {
            int totalCost = 0;
            Map<Enchantment, Integer> enchantmentCosts = EnchantmentCosts.getEnchantmentCosts(rightItem);

            for (Enchantment enchantment : leftEnchantments.keySet()) {
                totalCost += enchantmentCosts.getOrDefault(enchantment, 0);
            }
            System.out.println(totalCost);

            if (totalCost > event.getInventory().getMaximumRepairCost()) {
                int finalTotalCoast = totalCost;
                event.setResult(null); // Cancel the anvil operation
                int maxCost = event.getInventory().getMaximumRepairCost();
                event.getViewers().forEach(viewer -> {
                    if (viewer instanceof Player) {
                        ((Player) viewer).sendRawMessage("The enchantment cost is " + finalTotalCoast + ", but the maximum cost is " + maxCost + ".");
                    }
                });
                return;
            }

            ItemStack result = leftItem.clone();
            result.addEnchantments(rightEnchantments);
            event.setResult(result);
        }
    }
}

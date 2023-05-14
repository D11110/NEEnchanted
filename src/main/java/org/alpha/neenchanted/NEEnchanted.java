package org.alpha.neenchanted;

import org.bukkit.plugin.java.JavaPlugin;

public class NEEnchanted extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("NEEnchanted has been enabled!");

        // Register event listener
        getServer().getPluginManager().registerEvents(new Enchantments(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("NEEnchanted has been disabled!");
    }
}

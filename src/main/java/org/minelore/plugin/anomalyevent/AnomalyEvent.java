package org.minelore.plugin.anomalyevent;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class AnomalyEvent extends JavaPlugin {
    public Logger logger;

    @Override
    public void onEnable() {
        logger = getLogger();
        logger.info("AnomalyEvent plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        logger.info("AnomalyEvent plugin has been disabled!");
        super.onDisable();
    }
}

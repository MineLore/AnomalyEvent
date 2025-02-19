package org.minelore.plugin.anomalyevent.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.minelore.plugin.anomalyevent.AnomalyEvent;
import org.minelore.plugin.anomalyevent.anomaly.Anomaly;
import org.minelore.plugin.anomalyevent.registry.CooldownRegistry;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
* @author TheDiVaZo
* created on 15.02.2025
*/
public class AnomalyManager {
    private final AnomalyEvent plugin;
    private final CooldownRegistry<Anomaly<Player>> anomalyCooldownRegistry;
    private final CooldownRegistry<UUID> playerCooldownRegistry;

    private final Map<Anomaly<?>, BukkitTask> tasks = new HashMap<>();

    public AnomalyManager(
            AnomalyEvent plugin, CooldownRegistry<Anomaly<Player>> globalAnomalyCooldownRegistry,
            CooldownRegistry<Anomaly<Player>> playerAnomalyCooldownRegistry, CooldownRegistry<Anomaly<Player>> anomalyCooldownRegistry,
            CooldownRegistry<UUID> playerCooldownRegistry
    ) {
        this.plugin = plugin;
        this.anomalyCooldownRegistry = anomalyCooldownRegistry;
        this.playerCooldownRegistry = playerCooldownRegistry;
    }


    public boolean startAnomaly(Anomaly<Player> anomaly, Duration duration) {

        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                anomaly.activate(onlinePlayer);
            }
        }, 0, duration.getSeconds() * 20);

        tasks.put(anomaly, task);
        return true;
    }

    public boolean stopAnomaly(Anomaly<Player> anomaly) {
        BukkitTask bukkitTask = tasks.get(anomaly);
        if (bukkitTask != null) bukkitTask.cancel();
        return false;
    }
}

package org.minelore.plugin.anomalyevent.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.minelore.plugin.anomalyevent.AnomalyEvent;
import org.minelore.plugin.anomalyevent.anomaly.Anomaly;
import org.minelore.plugin.anomalyevent.util.CooldownRegistry;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
* @author TheDiVaZo
* created on 15.02.2025
*/
public class AnomalyManager {
    private final AnomalyEvent plugin;
    private final CooldownRegistry<Anomaly<Player>> globalAnomalyCooldownRegistry;
    private final CooldownRegistry<Anomaly<Player>> playerAnomalyCooldownRegistry;
    private final CooldownRegistry<UUID> playerCooldownRegistry;

    private final Map<Anomaly<?>, BukkitTask> tasks = new HashMap<>();

    public AnomalyManager(
            AnomalyEvent plugin, CooldownRegistry<Anomaly<Player>> globalAnomalyCooldownRegistry,
            CooldownRegistry<Anomaly<Player>> playerAnomalyCooldownRegistry,
            CooldownRegistry<UUID> playerCooldownRegistry
    ) {
        this.plugin = plugin;
        this.globalAnomalyCooldownRegistry = globalAnomalyCooldownRegistry;
        this.playerAnomalyCooldownRegistry = playerAnomalyCooldownRegistry;
        this.playerCooldownRegistry = playerCooldownRegistry;
    }


    public boolean startGlobalAnomaly(Anomaly<Player> anomaly, Duration duration) {

        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                anomaly.activate(onlinePlayer);
            }
        }, 0, duration.getSeconds() * 20);

        tasks.put(anomaly, task);
        return true;
    }

    public boolean stopGlobalAnomaly(Anomaly<Player> anomaly) {
        BukkitTask bukkitTask = tasks.get(anomaly);
        if (bukkitTask != null) bukkitTask.cancel();
    }

    public void startPlayerAnomaly(Anomaly<?> anomaly) {

    }
}

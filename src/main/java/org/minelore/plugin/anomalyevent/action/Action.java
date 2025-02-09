package org.minelore.plugin.anomalyevent.action;

import org.bukkit.entity.Player;

public interface Action {
    void interact(Player player);
    void cancel(Player player);
}

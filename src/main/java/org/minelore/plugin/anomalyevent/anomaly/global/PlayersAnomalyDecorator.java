package org.minelore.plugin.anomalyevent.anomaly.global;

import org.bukkit.entity.Player;
import org.minelore.plugin.anomalyevent.anomaly.local.LocalAnomaly;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author TheDiVaZo
 * created on 01.03.2025
 */
public class PlayersAnomalyDecorator implements GlobalAnomaly {
    private final String name;
    private final LocalAnomaly<Player> localAnomaly;
    private final Supplier<Collection<? extends Player>> collectionSupplier;

    public PlayersAnomalyDecorator(String name, LocalAnomaly<Player> localAnomaly, Supplier<Collection<? extends Player>> collectionSupplier) {
        this.name = name;
        this.localAnomaly = localAnomaly;
        this.collectionSupplier = collectionSupplier;
    }

    public PlayersAnomalyDecorator(LocalAnomaly<Player> localAnomaly, Supplier<Collection<? extends Player>> collectionSupplier) {
        this(localAnomaly.getName(), localAnomaly, collectionSupplier);
    }

    @Override
    public void activate() {
        for (Player player : collectionSupplier.get()) {
            localAnomaly.activate(player);
        }
    }

    @Override
    public void deactivate() {
        if (localAnomaly instanceof LocalAnomaly.Deactivable<Player> deactivableAnomaly) {
            deactivableAnomaly.deactivateAll();
        }
    }

    @Override
    public boolean hasActive() {
        if (localAnomaly instanceof LocalAnomaly.Deactivable<Player> deactivableAnomaly) {
            return deactivableAnomaly.hasActive();
        }
        else return false;
    }

    @Override
    public String getName() {
        return name;
    }
}

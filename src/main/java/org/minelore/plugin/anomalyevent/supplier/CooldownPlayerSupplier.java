package org.minelore.plugin.anomalyevent.supplier;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.minelore.plugin.anomalyevent.util.CooldownRegistry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;

public class CooldownPlayerSupplier implements Supplier<Player> {
    private final Duration cooldownDuration;
    private final Supplier<Collection<? extends Player>> playersSupplier;
    private final CooldownRegistry<UUID> cooldowns;

    public CooldownPlayerSupplier(Duration cooldownDuration, Supplier<Collection<? extends Player>> playersSupplier, CooldownRegistry<UUID> cooldowns) {
        this.cooldownDuration = cooldownDuration;
        this.cooldowns = cooldowns;
        this.playersSupplier = playersSupplier;
    }

    @Override
    @Nullable
    public Player get() {
        Optional<? extends Player> playerOptional = playersSupplier.get().stream().filter(player -> !cooldowns.isOnCooldown(player.getUniqueId())).findAny();
        if (playerOptional.isPresent()) {
            cooldowns.add(playerOptional.get().getUniqueId(), cooldownDuration);
            return playerOptional.get();
        }
        return null;
    }
}

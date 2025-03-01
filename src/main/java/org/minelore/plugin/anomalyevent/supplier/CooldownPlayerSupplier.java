package org.minelore.plugin.anomalyevent.supplier;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;

public class CooldownPlayerSupplier implements Supplier<Player> {
    private final Duration cooldownDuration;
    private final Supplier<Collection<? extends Player>> playersSupplier;
    private final Map<UUID, LocalDateTime> cooldowns;

    public CooldownPlayerSupplier(Duration cooldownDuration, Supplier<Collection<? extends Player>> playersSupplier, Map<UUID, LocalDateTime> cooldowns) {
        this.cooldownDuration = cooldownDuration;
        this.cooldowns = new HashMap<>(cooldowns);
        this.playersSupplier = playersSupplier;
    }

    public CooldownPlayerSupplier(Duration cooldownDuration, Supplier<Collection<? extends Player>> playersSupplier) {
        this.cooldownDuration = cooldownDuration;
        this.playersSupplier = playersSupplier;
        this.cooldowns = new HashMap<>();
    }

    private void addPlayerCooldown(Player player) {
        cooldowns.put(player.getUniqueId(), LocalDateTime.now().plus(cooldownDuration));
    }

    private boolean hasInCooldown(Player player) {
        LocalDateTime endTime = cooldowns.get(player.getUniqueId());
        if (endTime == null) return false;
        return endTime.isAfter(LocalDateTime.now());
    }

    @Override
    @Nullable
    public Player get() {
        Optional<? extends Player> playerOptional = playersSupplier.get().stream().filter(this::hasInCooldown).findAny();
        playerOptional.ifPresent(this::addPlayerCooldown);
        return playerOptional.orElse(null);
    }
}

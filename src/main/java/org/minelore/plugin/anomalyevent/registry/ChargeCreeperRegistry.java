package org.minelore.plugin.anomalyevent.registry;

import org.bukkit.entity.LivingEntity;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Consumer;

public class ChargeCreeperRegistry<T extends LivingEntity> implements CooldownRegistry<T> {
    @Override
    public void register(T object, Duration duration) {
        
    }
    @Override
    public void register(T object, Duration duration, Consumer<T> endCooldown) {

    }
    @Override
    public boolean isOnCooldown(T object) {
        return false;
    }
    @Override
    public boolean removeCooldown(T object) {
        return false;
    }
    @Override
    public Optional<Duration> getRemainingDuration(T object) {
        return Optional.empty();
    }
}

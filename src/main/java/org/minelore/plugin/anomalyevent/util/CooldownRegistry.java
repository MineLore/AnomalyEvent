package org.minelore.plugin.anomalyevent.util;

import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Consumer;

public interface CooldownRegistry<T> {
    void add(T object, Duration duration);
    @Nullable LocalDateTime getEndCooldown(T object);
    boolean isOnCooldown(T object);
    boolean removeCooldown(T object);
    Optional<Duration> getRemainingDuration(T object);
}

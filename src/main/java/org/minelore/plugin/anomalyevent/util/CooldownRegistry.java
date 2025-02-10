package org.minelore.plugin.anomalyevent.util;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Consumer;

public interface CooldownRegistry<T> {
    void register(T object, Duration duration);
    void register(T object, Duration duration, Consumer<T> endCooldown);
    boolean isOnCooldown(T object);
    boolean removeCooldown(T object);
    Optional<Duration> getRemainingDuration(T object);
}

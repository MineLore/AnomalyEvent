package org.minelore.plugin.anomalyevent.config.anomalies;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.time.Duration;

@ConfigSerializable
public class LaunchSetting {
    private boolean hasActive;
    private Duration cooldown;
}

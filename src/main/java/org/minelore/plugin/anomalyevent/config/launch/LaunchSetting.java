package org.minelore.plugin.anomalyevent.config.launch;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.time.Duration;

@ConfigSerializable
public class LaunchSetting {
    private String anomalyName;
    private boolean hasActive;
    private Duration cooldown;
}

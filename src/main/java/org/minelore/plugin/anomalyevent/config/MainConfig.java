package org.minelore.plugin.anomalyevent.config;

import org.minelore.plugin.anomalyevent.config.anomalies.AnomalySetting;
import org.minelore.plugin.anomalyevent.config.launch.LaunchSetting;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.List;
import java.util.Map;

@ConfigSerializable
public class MainConfig {
    /**
     * Список аномалий
     */
    private Map<String, AnomalySetting> anomalies;
    /**
     * Период активаций. Каждые `periodActivation` тиков будет происходить попытка активации аномалии
     */
    private int periodActivation;
    /**
     * Настройка запусков аномалий
     */
    private List<LaunchSetting> launchSettings;
}

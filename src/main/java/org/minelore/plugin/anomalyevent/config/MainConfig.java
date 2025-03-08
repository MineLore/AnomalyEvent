package org.minelore.plugin.anomalyevent.config;

import org.minelore.plugin.anomalyevent.config.anomalies.AnomalySetting;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

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
}

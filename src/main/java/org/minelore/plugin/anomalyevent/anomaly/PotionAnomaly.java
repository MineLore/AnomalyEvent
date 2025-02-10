package org.minelore.plugin.anomalyevent.anomaly;

import org.bukkit.entity.LivingEntity;
import org.minelore.plugin.anomalyevent.data.PotionData;

/**
 * Аномалия на наложение зелий
 * @param <T> класс цели, на которую накладывают зелья. Например, {@link org.bukkit.entity.Player}
 */
public class PotionAnomaly<T extends LivingEntity> extends FromDataAnomaly<PotionData, T> {
    public PotionAnomaly(String name, PotionData data) {
        super(name, data);
    }

    @Override
    public void activate(LivingEntity target) {
        target.addPotionEffect(data.potionEffect);
    }
}

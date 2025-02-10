package org.minelore.plugin.anomalyevent.activator;

import org.bukkit.entity.LivingEntity;
import org.minelore.plugin.anomalyevent.data.PotionData;

public class PotionAnomaly<T extends LivingEntity> extends FromDataAnomaly<PotionData, T> {
    public PotionAnomaly(String name, PotionData data) {
        super(name, data);
    }

    @Override
    public void activate(LivingEntity target) {
        target.addPotionEffect(data.potionEffect);
    }
}

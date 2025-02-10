package org.minelore.plugin.anomalyevent.data;

import org.bukkit.potion.PotionEffect;

public class PotionData extends AnomalyData {
    public PotionEffect potionEffect;

    public PotionData(PotionEffect potionEffect) {
        this.potionEffect = potionEffect;
    }

}

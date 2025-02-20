package org.minelore.plugin.anomalyevent.anomaly;

import com.google.common.base.Objects;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

/**
 * Аномалия на наложение зелий
 * @param <T> класс цели, на которую накладывают зелья. Например, {@link org.bukkit.entity.Player}
 */
public class PotionAnomaly<T extends LivingEntity> extends AbstractAnomaly<T> {
    private final PotionEffect potionEffect;

    public PotionAnomaly(String name, PotionEffect potionEffect) {
        super(name);
        this.potionEffect = potionEffect;
    }

    @Override
    public void activate(LivingEntity target) {
        target.addPotionEffect(potionEffect);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PotionAnomaly<?> that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(potionEffect, that.potionEffect);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), potionEffect);
    }
}

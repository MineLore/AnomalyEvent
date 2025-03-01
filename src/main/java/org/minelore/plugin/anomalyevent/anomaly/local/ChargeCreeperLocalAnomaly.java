package org.minelore.plugin.anomalyevent.anomaly.local;

import com.google.common.base.Objects;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Аномалия на превращение обычного крипера в зараженного
 * @param <T> класс цели, во круг которой криперы будут превращаться. Например, {@link org.bukkit.entity.Player}
 */
public class ChargeCreeperLocalAnomaly<T extends LivingEntity> extends AbstractLocalAnomaly<T> {
    public final double distance;
    public final int creeperCount;
    public final double probability;

    public ChargeCreeperLocalAnomaly(String name, double distance, int creeperCount, double probability) {
        super(name);
        this.distance = distance;
        this.creeperCount = creeperCount;
        this.probability = probability;
    }

    @Override
    public void activate(T target) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Arrays.stream(target.getChunk().getEntities())
                .filter(entity -> entity.getLocation().distance(target.getLocation()) <= distance
                        && entity.getType().equals(EntityType.CREEPER))
                .limit(creeperCount)
                .filter(entity -> random.nextDouble() <= probability)
                .map(Creeper.class::cast)
                .forEach(creeper -> creeper.setPowered(true));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ChargeCreeperLocalAnomaly<?> that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(distance, that.distance) == 0 && creeperCount == that.creeperCount && Double.compare(probability, that.probability) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), distance, creeperCount, probability);
    }
}

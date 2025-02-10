package org.minelore.plugin.anomalyevent.anomaly;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.minelore.plugin.anomalyevent.data.ChargeCreeperData;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Аномалия на превращение обычного крипера в зараженного
 * @param <T> класс цели, во круг которой криперы будут превращаться. Например, {@link org.bukkit.entity.Player}
 */
public class ChargeCreeperAnomaly<T extends LivingEntity> extends FromDataAnomaly<ChargeCreeperData, T> {
    public ChargeCreeperAnomaly(String name, ChargeCreeperData data) {
        super(name, data);
    }

    @Override
    public void activate(T target) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Arrays.stream(target.getChunk().getEntities())
                .filter(entity -> entity.getLocation().distance(target.getLocation()) <= data.distance
                        && entity.getType().equals(EntityType.CREEPER))
                .limit(data.creeperCount)
                .filter(entity -> random.nextDouble() <= data.probability)
                .map(Creeper.class::cast)
                .forEach(creeper -> creeper.setPowered(true));
    }
}

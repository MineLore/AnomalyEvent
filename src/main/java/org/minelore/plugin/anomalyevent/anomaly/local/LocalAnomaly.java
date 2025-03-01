package org.minelore.plugin.anomalyevent.anomaly.local;

import org.minelore.plugin.anomalyevent.anomaly.Anomaly;

/**
 * Интерфейс для работы с "аномалией"
 * @param <T> цель для аномалии. Может быть игроком, а может быть, например, локацией
 */
public interface LocalAnomaly<T> extends Anomaly {
    void activate(T target);

    interface Deactivable<T> extends LocalAnomaly<T> {
        void deactivate(T target);
        void deactivateAll();
        boolean hasActivate(T target);
        ImpactAnomaly<T> getActivated(T target);
    }
}

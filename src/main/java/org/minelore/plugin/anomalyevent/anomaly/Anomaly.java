package org.minelore.plugin.anomalyevent.anomaly;

import java.time.Duration;

/**
 * Интерфейс для работы с "аномалией"
 * @param <T> цель для аномалии. Может быть игроком, а может быть, например, локацией
 */
public interface Anomaly<T> {
    String getName();
    void activate(T target);

    interface Deactivable<T> extends Anomaly<T> {
        void deactivate(T target);
        void deactivateAll();
        boolean hasActivate(T target);
        AnomalyActivated<T> getActivate(T target);
    }
}

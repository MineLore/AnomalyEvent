package org.minelore.plugin.anomalyevent.launcher;

import org.minelore.plugin.anomalyevent.anomaly.local.LocalAnomaly;
import org.minelore.plugin.anomalyevent.launcher.condition.ConditionLaunch;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
* @author TheDiVaZo
* created on 20.02.2025
*/
public interface LocalLauncher<T, A extends LocalAnomaly<T>> extends Launcher<A> {
    boolean launch(T target);

    static <T, A extends LocalAnomaly<T>> AnomalyBuilder<T, A> builder() {
        return new Builder<>();
    }

    interface AnomalyBuilder<T, A extends LocalAnomaly<T>> {
        ConditionLaunchBuilder<T, A> anomaly(A anomaly);
    }

    interface ConditionLaunchBuilder<T, A extends LocalAnomaly<T>> {
        ConditionLaunchBuilder<T, A> addConditionLaunch(Function<A, ConditionLaunch<A>> conditionLaunchFunction);
        LocalLauncher<T, A> build();
    }

    class Builder<T, A extends LocalAnomaly<T>> implements AnomalyBuilder<T, A>, ConditionLaunchBuilder<T, A> {
        private final List<ConditionLaunch<A>> conditionLaunches = new LinkedList<>();
        private A anomaly;

        @Override
        public ConditionLaunchBuilder<T, A> anomaly(A anomaly) {
            this.anomaly = anomaly;
            return this;
        }

        @Override
        public ConditionLaunchBuilder<T, A> addConditionLaunch(Function<A, ConditionLaunch<A>> conditionLaunchFunction) {
            if (anomaly == null) throw new IllegalStateException("Anomaly has not been null in condition launch builder");
            ConditionLaunch<A> conditionLaunch = conditionLaunchFunction.apply(anomaly);
            if (conditionLaunch.getAnomaly() != anomaly) throw new IllegalStateException("Condition launch must contain the object passed to the generator");
            conditionLaunches.add(conditionLaunch);
            return this;
        }

        @Override
        public LocalLauncher<T, A> build() {
            return new LocalLauncherImpl<>(anomaly, conditionLaunches);
        }
    }
}

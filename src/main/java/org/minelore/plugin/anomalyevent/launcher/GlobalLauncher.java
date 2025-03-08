package org.minelore.plugin.anomalyevent.launcher;

import org.minelore.plugin.anomalyevent.anomaly.global.GlobalAnomaly;
import org.minelore.plugin.anomalyevent.anomaly.local.LocalAnomaly;
import org.minelore.plugin.anomalyevent.launcher.condition.ConditionLaunch;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * @author TheDiVaZo
 * created on 01.03.2025
 */
public interface GlobalLauncher<A extends GlobalAnomaly> extends Launcher<A> {
    boolean launch();

    static <A extends GlobalAnomaly> GlobalLauncher.AnomalyBuilder<A> builder() {
        return new GlobalLauncher.Builder<>();
    }

    interface AnomalyBuilder<A extends GlobalAnomaly> {
        GlobalLauncher.ConditionLaunchBuilder<A> anomaly(A anomaly);
    }

    interface ConditionLaunchBuilder<A extends GlobalAnomaly> {
        GlobalLauncher.ConditionLaunchBuilder<A> addConditionLaunch(Function<A, ConditionLaunch<A>> conditionLaunchFunction);
        GlobalLauncher<A> build();
    }

    class Builder<A extends GlobalAnomaly> implements GlobalLauncher.AnomalyBuilder<A>, GlobalLauncher.ConditionLaunchBuilder<A> {
        private final List<ConditionLaunch<A>> conditionLaunches = new LinkedList<>();
        private A anomaly;

        @Override
        public GlobalLauncher.ConditionLaunchBuilder<A> anomaly(A anomaly) {
            this.anomaly = anomaly;
            return this;
        }

        @Override
        public GlobalLauncher.ConditionLaunchBuilder<A> addConditionLaunch(Function<A, ConditionLaunch<A>> conditionLaunchFunction) {
            if (anomaly == null) throw new IllegalStateException("Anomaly has not been null in condition launch builder");
            ConditionLaunch<A> conditionLaunch = conditionLaunchFunction.apply(anomaly);
            if (conditionLaunch.getAnomaly() != anomaly) throw new IllegalStateException("Condition launch must contain the object passed to the generator");
            conditionLaunches.add(conditionLaunch);
            return this;
        }

        @Override
        public GlobalLauncher<A> build() {
            return new GlobalLauncherImpl<>(anomaly, conditionLaunches);
        }
    }
}

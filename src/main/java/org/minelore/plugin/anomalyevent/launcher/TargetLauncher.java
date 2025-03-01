package org.minelore.plugin.anomalyevent.launcher;

import org.minelore.plugin.anomalyevent.anomaly.local.LocalAnomaly;

import java.util.Iterator;
import java.util.function.Supplier;

/**
 * @author TheDiVaZo
 * created on 20.02.2025
 */
public class TargetLauncher<T, A extends LocalAnomaly<T>> implements Launcher<A> {
    protected final Supplier<Iterator<T>> iteratorSupplier;

    public TargetLauncher(Supplier<Iterator<T>> iteratorSupplier) {
        this.iteratorSupplier = iteratorSupplier;
    }

    @Override
    public boolean launch(A anomaly) {
        Iterator<T> iterator = iteratorSupplier.get();
        boolean anyLaunch = false;
        while (iterator.hasNext()) {
            anomaly.activate(iterator.next());
            anyLaunch = true;
        }
        return anyLaunch;
    }
}

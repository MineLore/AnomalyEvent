package org.minelore.plugin.anomalyevent.activator;

import org.minelore.plugin.anomalyevent.data.AnomalyData;

public abstract class FromDataAnomaly<D extends AnomalyData, T> implements Anomaly<T> {

    protected final String name;
    protected final D data;

    public FromDataAnomaly(String name, D data) {
        this.name = name;
        this.data = data;
    }

    public D getData() {
        return data;
    }

    @Override
    public String getName() {
        return name;
    }
}

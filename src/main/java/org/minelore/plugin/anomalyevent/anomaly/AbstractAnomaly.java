package org.minelore.plugin.anomalyevent.anomaly;

import com.google.common.base.Objects;

public abstract class AbstractAnomaly<T> implements Anomaly<T> {

    protected final String name;

    protected AbstractAnomaly(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AbstractAnomaly<?> that)) return false;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

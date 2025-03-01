package org.minelore.plugin.anomalyevent.anomaly.local;

import com.google.common.base.Objects;

public abstract class AbstractLocalAnomaly<T> implements LocalAnomaly<T> {

    protected final String name;

    protected AbstractLocalAnomaly(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AbstractLocalAnomaly<?> that)) return false;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

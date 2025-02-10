package org.minelore.plugin.anomalyevent.util;

import org.jetbrains.annotations.Range;

import java.util.Collection;
import java.util.SequencedCollection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public final class FilterUtil {
    public static <T> Stream<T> filterRandom(Stream<T> self, @Range(from = 0, to = 1) double percent) {
        if (percent < 0 || percent > 1) {
            throw new IllegalArgumentException("Percent must be between 0 and 1");
        }
        return self.filter(ignore -> ThreadLocalRandom.current().nextDouble() <= percent);
    }

    public static <T> T findRandom(Collection<T> self, IntFunction<T[]> intFunction) {
        if (self.isEmpty()) {
            throw new IllegalStateException("Collection is empty");
        }
        return self.toArray(intFunction)[ThreadLocalRandom.current().nextInt(self.size())];
    }
}

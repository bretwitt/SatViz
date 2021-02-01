package io.github.bretwitt.mathematics.units;

import io.github.bretwitt.mathematics.units.base.distance.DistanceUnit;
import io.github.bretwitt.mathematics.units.base.time.TimeUnit;

public abstract class UnitSystem {

    private final DistanceUnit distanceUnit;
    private final TimeUnit timeUnit;

    public UnitSystem(TimeUnit timeUnit, DistanceUnit distanceUnit) {
        this.timeUnit = timeUnit;
        this.distanceUnit = distanceUnit;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public DistanceUnit getDistanceUnit() {
        return distanceUnit;
    }
}

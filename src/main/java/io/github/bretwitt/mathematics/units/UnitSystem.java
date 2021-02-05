package io.github.bretwitt.mathematics.units;

import io.github.bretwitt.mathematics.units.base.distance.DistanceUnit;
import io.github.bretwitt.mathematics.units.base.speed.SpeedUnit;
import io.github.bretwitt.mathematics.units.base.time.TimeUnit;
import org.checkerframework.checker.units.qual.Speed;

public abstract class UnitSystem {

    private final DistanceUnit distanceUnit;
    private final TimeUnit timeUnit;
    private final SpeedUnit speedUnit;

    public UnitSystem(TimeUnit timeUnit, DistanceUnit distanceUnit, SpeedUnit speedUnit) {
        this.timeUnit = timeUnit;
        this.distanceUnit = distanceUnit;
        this.speedUnit = speedUnit;
    }

    public SpeedUnit getSpeedUnit() {return speedUnit;}

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public DistanceUnit getDistanceUnit() {
        return distanceUnit;
    }
}

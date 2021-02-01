package io.github.bretwitt.mathematics.units.astrodynamicunit;

import io.github.bretwitt.mathematics.units.base.time.TimeUnit;

public class DistanceEarthTimeUnit extends TimeUnit {


    @Override
    public float toTU() {
        return getValue();
    }

    @Override
    public float fromTU(float value) {
        return getValue();
    }
}

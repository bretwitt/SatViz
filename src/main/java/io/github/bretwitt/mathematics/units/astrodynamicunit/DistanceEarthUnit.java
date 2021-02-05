package io.github.bretwitt.mathematics.units.astrodynamicunit;

import io.github.bretwitt.mathematics.units.base.distance.DistanceUnit;

public class DistanceEarthUnit extends DistanceUnit {

    @Override
    public float toDUE() {
        return getValue();
    }

    @Override
    public float fromDUE() {
        return getValue();
    }

    @Override
    public float fromDUE(float due) {
        return due;
    }

    @Override
    public float toDUE(float due) {
        return due;
    }
}

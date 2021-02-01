package io.github.bretwitt.mathematics.units.base.distance;

import io.github.bretwitt.mathematics.units.Unit;

public abstract class DistanceUnit extends Unit {

    public abstract float toDUE();

    public abstract float fromDUE();

    public abstract float toDUE(float due);

}

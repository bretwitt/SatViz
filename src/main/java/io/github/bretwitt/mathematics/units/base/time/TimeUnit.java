package io.github.bretwitt.mathematics.units.base.time;

import io.github.bretwitt.mathematics.units.Unit;

public abstract class TimeUnit extends Unit {

    public abstract float toTU();

    public abstract float toTU(float value);

    public abstract float fromTU(float value);

}

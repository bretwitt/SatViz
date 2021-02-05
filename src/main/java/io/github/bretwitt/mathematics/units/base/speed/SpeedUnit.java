package io.github.bretwitt.mathematics.units.base.speed;

import io.github.bretwitt.mathematics.units.Unit;

public abstract class SpeedUnit extends Unit {

    public abstract float toTUDU();

    public abstract float toTUDU(float value);


    public abstract float fromTUDU(float value);

}

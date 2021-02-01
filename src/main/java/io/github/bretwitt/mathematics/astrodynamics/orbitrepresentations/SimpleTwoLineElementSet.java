package io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations;

import io.github.bretwitt.mathematics.units.UnitSystem;
import io.github.bretwitt.mathematics.units.base.distance.DistanceUnit;
import io.github.bretwitt.mathematics.units.base.time.TimeUnit;

public class SimpleTwoLineElementSet {

    private final float e;
    private final float i;
    private final float raan;
    private final float aop;
    private final float n;
    private final float MA;
    private UnitSystem units;

    public SimpleTwoLineElementSet(float e, float i, float raan, float aop, float n, float MA) {
        this.e = e;
        this.i = i;
        this.raan = raan;
        this.aop = aop;
        this.n = n;
        this.MA = MA;
    }

    public SimpleTwoLineElementSet(float e, float i, float raan, float aop, float n, float MA, UnitSystem units) {
        this.units = units;
        DistanceUnit distanceUnit = units.getDistanceUnit();
        this.e = distanceUnit.toDUE(e);
        this.i = distanceUnit.toDUE(i);
        this.raan = distanceUnit.toDUE(raan);
        this.aop = distanceUnit.toDUE(aop);
        this.n = distanceUnit.toDUE(n);
        this.MA = distanceUnit.toDUE(MA);
    }
    public float getE() {
        return e;
    }

    public float getI() {
        return i;
    }

    public float getRaan() {
        return raan;
    }

    public float getN() {
        return n;
    }

    public float getAoP() {
        return aop;
    }

    public float getMA() {
        return MA;
    }
}

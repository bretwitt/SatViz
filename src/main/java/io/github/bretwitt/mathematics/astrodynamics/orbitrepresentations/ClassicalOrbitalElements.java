package io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations;

import com.jme3.math.FastMath;
import io.github.bretwitt.mathematics.units.UnitSystem;
import io.github.bretwitt.mathematics.units.base.distance.DistanceUnit;
import io.github.bretwitt.mathematics.units.base.time.TimeUnit;

public class ClassicalOrbitalElements {

    float e;
    float i;
    float raan;
    float tae;
    float a;
    float aop;
    UnitSystem units;

    public ClassicalOrbitalElements(float a, float e, float i, float raan, float aop, float tae, UnitSystem units){
        DistanceUnit distanceUnit = units.getDistanceUnit();
        this.a = distanceUnit.toDUE(a);
        this.e = distanceUnit.toDUE(e);
        this.i = distanceUnit.toDUE(i);
        this.raan = distanceUnit.toDUE(raan);
        this.aop = distanceUnit.toDUE(aop);
        this.tae = distanceUnit.toDUE(tae);
    }

    public ClassicalOrbitalElements(float a,float e,float i,float raan,float aop,float tae) {
        this.a = a;
        this.e = e;
        this.i = i;
        this.raan = raan;
        this.aop = aop;
        this.tae = tae;
    }

    public UnitSystem getUnitSystem() {
        return units;
    }

    public void setE(float e) {
        this.e = e;
    }

    public float getP() {
        return a * (1 - FastMath.pow(e,2));
    }

    public float getA() { return a; }

    public float getE() {
        return e;
    }

    public float getI() {
        return i;
    }

    public float getRAAN() {
        return raan;
    }

    public float getTAE() { return tae; }

    public float getAoP() {
        return aop;
    }
}

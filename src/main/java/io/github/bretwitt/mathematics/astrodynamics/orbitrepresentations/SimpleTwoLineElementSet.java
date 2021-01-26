package io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations;

public class SimpleTwoLineElementSet {

    private final float e;
    private final float i;
    private final float raan;
    private final float aop;
    private final float n;
    private final float MA;

    public SimpleTwoLineElementSet(float e, float i, float raan, float aop, float n, float MA) {
        this.e = e;
        this.i = i;
        this.raan = raan;
        this.aop = aop;
        this.n = n;
        this.MA = MA;
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

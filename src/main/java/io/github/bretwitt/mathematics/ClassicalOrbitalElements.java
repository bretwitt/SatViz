package io.github.bretwitt.mathematics;

import com.jme3.math.FastMath;

public class ClassicalOrbitalElements {

    float e;
    float i;
    float raan;
    float tae;
    float a;

    public ClassicalOrbitalElements(float a, float e, float i, float raan, float tae){
        this.a = a;
        this.e = e;
        this.i = i;
        this.raan = raan;
        this.tae = tae;
    }

    public void setE(float e) {
        this.e = e;
    }

    public void setI(float i) {
        this.i = i;
    }

    public void setRaan(float raan) {
        this.raan = raan;
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
}

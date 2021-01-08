package io.github.bretwitt.mathematics;

public class ClassicalOrbitalElements {

    float p;
    float e;
    float i;
    float raan;

    public ClassicalOrbitalElements(float p, float e, float i, float raan){
        this.p = p;
        this.e = e;
        this.i = i;
        this.raan = raan;
    }

    public void setP(float p) {
        this.p = p;
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
        return p;
    }

    public float getE() {
        return e;
    }

    public float getI() {
        return i;
    }

    public float getRAAN() {
        return raan;
    }

}

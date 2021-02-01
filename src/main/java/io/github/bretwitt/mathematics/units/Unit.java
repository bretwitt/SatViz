package io.github.bretwitt.mathematics.units;

public abstract class Unit {

    private float distance;

    public void setValue(float dist) {
        distance = dist;
    }

    public float getValue() {
        return distance;
    }

}

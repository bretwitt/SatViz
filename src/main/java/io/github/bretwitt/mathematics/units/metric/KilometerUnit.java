package io.github.bretwitt.mathematics.units.metric;

import io.github.bretwitt.mathematics.units.Unit;
import io.github.bretwitt.mathematics.units.UnitConversionUtils;
import io.github.bretwitt.mathematics.units.base.distance.DistanceUnit;

public class KilometerUnit extends DistanceUnit {

    @Override
    public float toDUE() {
        return getValue() * UnitConversionUtils.KMtoDUE;
    }

    @Override
    public float fromDUE() {
        return getValue() * UnitConversionUtils.DUEtoKM;
    }

    @Override
    public float fromDUE(float due) {
        return due * UnitConversionUtils.DUEtoKM;
    }

    @Override
    public float toDUE(float due) {
        return due * UnitConversionUtils.KMtoDUE;
    }
}

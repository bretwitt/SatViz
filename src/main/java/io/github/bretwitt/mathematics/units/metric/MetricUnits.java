package io.github.bretwitt.mathematics.units.metric;

import io.github.bretwitt.mathematics.units.UnitSystem;

public class MetricUnits extends UnitSystem {

    public MetricUnits() {
        super(new MetricTimeUnit(), new KilometerUnit());
    }

}

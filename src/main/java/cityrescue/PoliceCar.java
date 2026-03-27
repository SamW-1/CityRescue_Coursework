package cityrescue;

import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitType;

class PoliceCar extends Unit {
    public PoliceCar() {
        super(3);
    }
    @ Override
    public boolean canHandle(IncidentType type) {
        return false;
    }
    @ Override
    public int getTicksToResolve(int severity) {
        return 0;
    }
    @ Override
    public UnitType getUnitType() {
        return UnitType.POLICE_CAR;
    }
}
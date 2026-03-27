package cityrescue;

import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitType;

class FireEngine extends Unit {
    public FireEngine() {
        super(4);
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
        return UnitType.FIRE_ENGINE;
    }
}
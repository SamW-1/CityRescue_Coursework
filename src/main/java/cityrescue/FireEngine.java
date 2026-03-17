package cityrescue;

import cityrescue.enums.IncidentType;

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
}